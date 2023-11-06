package com.example.quran.data

import com.example.quran.domain.model.QuranEdition
import com.example.quran.domain.model.Surah
import com.example.quran.domain.repository.IQuranRepository
import com.example.quran.network.quran.QuranEditionItem
import com.example.quran.network.quran.SurahItem
import com.example.quran.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class QuranRepository (private val remoteDataSource: QuranRemotedataSource) : IQuranRepository {
    override fun getListSurah(): Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>() {
            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
              return remoteDataSource.getListSurah()
            }
        }.asFlow()
    }

    override fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItem>>() {
            override fun fetchFromNetwork(data: List<QuranEditionItem>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItem>>> {
                return remoteDataSource.getDetailSurahWithQuranEditions(number)
            }
        }.asFlow()
        }
    }
