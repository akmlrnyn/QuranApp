package com.example.quran.core.domain.model

import com.example.quran.core.data.Resource

data class DailyAdzanResult(
    val listLocation: List<String>,
    val  adzanTime: Resource<Jadwal>,
    val currentDate: List<String>
)
