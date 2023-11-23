package com.example.quran.presentation.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.quran.core.data.QuranRepository
import com.example.quran.core.data.Resource
import com.example.quran.core.domain.model.QuranEdition
import com.example.quran.core.domain.model.Surah

class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {

   fun getListSurah(): LiveData<Resource<List<Surah>>> =
       quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEditions(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}