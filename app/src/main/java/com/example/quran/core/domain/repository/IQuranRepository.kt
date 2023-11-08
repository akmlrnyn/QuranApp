package com.example.quran.core.domain.repository

import com.example.quran.core.data.Resource
import com.example.quran.core.domain.model.QuranEdition
import com.example.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>
}