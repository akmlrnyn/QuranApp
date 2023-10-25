package com.example.quran.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface QuranApiService {

    @GET("surah")
    fun getListSurah() : Call<SurahResponse>

    @GET("surah/{number}/editions/quran-uthmani,ar.alafasy,id.indonesian")
    fun getListAyahsBySurah(
        @Path("number") number: Int
    ) : Call<AyahResponse>
}