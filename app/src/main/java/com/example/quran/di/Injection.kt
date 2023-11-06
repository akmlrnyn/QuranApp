package com.example.quran.di

import com.example.quran.data.QuranRemotedataSource
import com.example.quran.data.QuranRepository
import com.example.quran.network.ApiConfig

object Injection {
    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.quranApiConfig
        val quranRemotedataSource = QuranRemotedataSource(quranApiService)
        return QuranRepository(quranRemotedataSource)
    }
}