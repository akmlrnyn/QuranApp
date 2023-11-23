package com.example.quran.core.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import java.util.Locale

class LocationPreferences(val context: Context) {
    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getKnownLastLocation(): LiveData<List<String>> {
        val lastKnownLocation = MutableLiveData<List<String>>()
        fusedLocation.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(context, Locale.getDefault())
                geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                ) { listAddress ->
                    val city = listAddress[0].subAdminArea
                    val arrCity = city.split(" ")
                    val subLocality = listAddress[0].subLocality
                    val locality = listAddress[0].locality
                    val resultLocation = "$subLocality, $locality"

                    val currentLanguage = Locale.getDefault().language
                    Log.i("LocPref", "getCurrentLanguage: $currentLanguage")

                    val cityResult: String = when (currentLanguage) {
                        "in" -> {
                            var result = ""
                            for (i in 1 until arrCity.size) {
                                result += arrCity[i] + " " //Aceh Barat Daya
                            }
                            result
                        }
                        "en" -> {
                            var result = ""
                            for (i in 0 until arrCity.size - 1) {
                                result += arrCity[i] + " "
                            }
                            result
                        }

                        else -> {
                            Log.e("LocPref", "error: current language is undefined", )
                            "Jakarta"
                        }
                    }

                    val listCity = listOf(cityResult, resultLocation)
                    Log.i("LocPref", "data: $listCity")
                    lastKnownLocation.postValue(listCity)
                }
            } else {
                Toast.makeText(context, "Sorry something wrong", Toast.LENGTH_SHORT).show()
            }
        }

        fusedLocation.lastLocation.addOnFailureListener{
            Log.e("SharedViewModel", "getKnownLastLocation: " + it.localizedMessage )
        }
        return lastKnownLocation
    }
}