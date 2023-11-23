package com.example.quran.core.data.network

import android.util.Log
import com.example.quran.core.data.network.adzan.AdzanApiService
import com.example.quran.core.data.network.adzan.CityItem
import com.example.quran.core.data.network.quran.QuranApiService
import com.example.quran.core.data.network.quran.QuranEditionItem
import com.example.quran.core.data.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemotedataSource (private val quranApiService: QuranApiService, private val adzanApiService: AdzanApiService) {
    suspend fun getListSurah() : Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = quranApiService.getListSurah()
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemotedataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val ayahResponse = quranApiService.getDetailSurahWithQuranEdition(number)
                val quranEditions = ayahResponse.quranEditionItem
                emit(NetworkResponse.Success(quranEditions))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemotedataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun searchCity(city : String) : Flow<NetworkResponse<List<CityItem>>> =
        flow {
            try {
                val cityResponse = adzanApiService.searchCity(city)
                val cities = cityResponse.listCity
                emit(NetworkResponse.Success(cities))
            }catch (e : Exception){
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemotedataSource::class.java.simpleName, "error " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)
}