package com.example.quran.presentation.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quran.network.ApiConfig
import com.example.quran.network.AyahResponse
import com.example.quran.network.SurahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class QuranViewModel : ViewModel() {
    private var _listSurah = MutableLiveData<SurahResponse>()
    val listSurah get() = _listSurah as LiveData<SurahResponse>

    private var _listAyahs = MutableLiveData<AyahResponse>()
    val listAyahs get() = _listAyahs as LiveData<SurahResponse>

    fun getListSurah() {
        ApiConfig.quranApiConfig.getListSurah().enqueue(object: Callback<SurahResponse> {
            override fun onResponse(call: Call<SurahResponse>, response: Response<SurahResponse>) {
                if (response.isSuccessful) {
                    _listSurah?.postValue(response.body())
                } else Log.e(
                    "QuranViewModel",
                    "onResponse: Error call Http request with status code" + response.code()
                )
            }

            override fun onFailure(call: Call<SurahResponse>, t: Throwable) {
                Log.e("QuranViewModel", "onFailure: " + t.localizedMessage)
            }
        })
    }

    fun getListAyahBySurah(number: Int) {
        ApiConfig.quranApiConfig.getListAyahsBySurah(number).enqueue(object:
        Callback<AyahResponse> {
            override fun onResponse(call: Call<AyahResponse>, response: Response<AyahResponse>) {
                if (response.isSuccessful) {
                    _listAyahs.postValue(response.body())
                } else Log.e(
                    "QuranViewModel",
                    "onResponse: Error call Http request with status code" + response.code()
                )
            }

            override fun onFailure(call: Call<SurahResponse>, t: Throwable) {
                Log.e("QuranViewModel", "onFailure: " + t.localizedMessage)
            }
        })
    }
}