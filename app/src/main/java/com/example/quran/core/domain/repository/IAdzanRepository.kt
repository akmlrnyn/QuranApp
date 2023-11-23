package com.example.quran.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.quran.core.data.Resource
import com.example.quran.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {
    fun getLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
}