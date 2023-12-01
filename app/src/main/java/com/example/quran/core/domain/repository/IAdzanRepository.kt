package com.example.quran.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.quran.core.data.Resource
import com.example.quran.core.domain.model.City
import com.example.quran.core.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow
import java.time.Month
import java.time.Year

interface IAdzanRepository {
    fun getLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
    fun getDailyAdzanTime(id: String, year: String, month: String, date: String): Flow<Resource<Jadwal>>
}