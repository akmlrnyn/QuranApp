package com.example.quran.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quran.core.data.AdzanRepository
import com.example.quran.core.data.Resource
import com.example.quran.core.domain.model.DailyAdzanResult

class AdzanViewModel(private val adzanRepository: AdzanRepository): ViewModel() {

    fun getDailyAdzanTime(): LiveData<Resource<DailyAdzanResult>> = adzanRepository.getDailyAdzanTime()
}