package com.example.quran.presentation.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.quran.core.data.QuranRepository
import com.example.quran.core.data.Resource
import com.example.quran.core.domain.model.QuranEdition
import com.example.quran.core.domain.model.Surah
import com.example.quran.core.network.ApiConfig
import com.example.quran.core.network.quran.AyahResponse
import com.example.quran.core.network.quran.SurahResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {

   fun getListSurah(): LiveData<Resource<List<Surah>>> =
       quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEditions(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}