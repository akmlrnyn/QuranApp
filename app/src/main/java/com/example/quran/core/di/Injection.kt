package com.example.quran.core.di

import android.content.Context
import com.example.quran.core.data.AdzanRepository
import com.example.quran.core.data.network.RemotedataSource
import com.example.quran.core.data.QuranRepository
import com.example.quran.core.data.local.CalendarPreferences
import com.example.quran.core.data.local.LocationPreferences
import com.example.quran.core.data.network.ApiConfig

object Injection {
    val quranApiService = ApiConfig.quranApiConfig
    val adzanApiService = ApiConfig.adzanApiConfig
    val remoteDataSource = RemotedataSource(quranApiService, adzanApiService)

    fun provideQuranRepository(): QuranRepository {
        val remotedataSource = RemotedataSource(quranApiService, adzanApiService)
        return QuranRepository(remotedataSource)
    }

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences, calendarPreferences)
    }
}