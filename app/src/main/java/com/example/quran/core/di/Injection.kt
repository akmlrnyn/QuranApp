package com.example.quran.core.di

import com.example.quran.core.data.QuranRemotedataSource
import com.example.quran.core.data.QuranRepository
import com.example.quran.core.network.ApiConfig

object Injection {
    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.quranApiConfig
        val quranRemotedataSource = QuranRemotedataSource(quranApiService)
        return QuranRepository(quranRemotedataSource)
    }
}