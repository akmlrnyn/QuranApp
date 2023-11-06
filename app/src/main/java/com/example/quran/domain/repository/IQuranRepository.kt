package com.example.quran.domain.repository

import com.example.quran.data.Resource
import com.example.quran.domain.model.QuranEdition
import com.example.quran.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>
}