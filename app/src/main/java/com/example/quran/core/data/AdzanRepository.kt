package com.example.quran.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.quran.core.data.local.LocationPreferences
import com.example.quran.core.data.network.NetworkBoundResource
import com.example.quran.core.data.network.NetworkResponse
import com.example.quran.core.data.network.RemotedataSource
import com.example.quran.core.domain.model.City
import com.example.quran.core.domain.model.DailyAdzanResult
import com.example.quran.core.domain.repository.IAdzanRepository
import com.example.quran.core.data.network.adzan.CityItem
import com.example.quran.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class AdzanRepository (
    private val remoteDataSource: RemotedataSource,
    private val locationPreferences: LocationPreferences
): IAdzanRepository {
    override fun getLocation(): LiveData<List<String>> = locationPreferences.getKnownLastLocation()

    override fun searchCity(city: String): Flow<Resource<List<City>>> {
        return object : NetworkBoundResource<List<City>, List<CityItem>>() {
            override fun fetchFromNetwork(data: List<CityItem>): Flow<List<City>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<CityItem>>> {
                return remoteDataSource.searchCity(city)
            }
        }.asFlow()
    }

    fun getDailyAdzanTime(): LiveData<Resource<DailyAdzanResult>> {
        val listLocation = getLocation()
        val listCity = listLocation.switchMap { location ->
            searchCity(location[0]).asLiveData()
        }
        val resultData = MediatorLiveData<Resource<DailyAdzanResult>>()

        resultData.addSource(listLocation) {
            resultData.value = combineLatestData(listLocation, listCity)
        }

        resultData.addSource(listCity) {
            resultData.value = combineLatestData(listLocation, listCity)
        }

        return resultData
    }

    private fun combineLatestData(
        listLocationResult: LiveData<List<String>>,
        listCityResult: LiveData<Resource<List<City>>>
    ): Resource<DailyAdzanResult> {
        val listLocation = listLocationResult.value
        val listCity = listCityResult.value

        return if (listLocation == null || listCity == null) {
            Resource.Loading()
        } else {
            try {
                val data = DailyAdzanResult(listLocation, listCity)
                Resource.Success(data)
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage)
            }
        }
    }

}