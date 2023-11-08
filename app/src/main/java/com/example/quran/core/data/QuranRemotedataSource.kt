package com.example.quran.core.data

import android.util.Log
import com.example.quran.core.network.quran.QuranApiService
import com.example.quran.core.network.quran.QuranEditionItem
import com.example.quran.core.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuranRemotedataSource (private val apiService: QuranApiService) {
    suspend fun getListSurah() : Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = apiService.getListSurah()
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(QuranRemotedataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val ayahResponse = apiService.getDetailSurahWithQuranEdition(number)
                val quranEditions = ayahResponse.quranEditionItem
                emit(NetworkResponse.Success(quranEditions))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(QuranRemotedataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)
}