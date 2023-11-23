package com.example.quran.core.domain.model

import com.example.quran.core.data.Resource

data class DailyAdzanResult(
    val listAdress: List<String>,
    val  listCity: Resource<List<City>>
)
