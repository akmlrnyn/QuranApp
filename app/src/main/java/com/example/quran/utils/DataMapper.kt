package com.example.quran.utils

import com.example.quran.core.data.network.adzan.CityItem
import com.example.quran.core.data.network.adzan.JadwalItem
import com.example.quran.core.domain.model.Ayah
import com.example.quran.core.domain.model.QuranEdition
import com.example.quran.core.domain.model.Surah
import com.example.quran.core.data.network.quran.AyahsItem
import com.example.quran.core.data.network.quran.QuranEditionItem
import com.example.quran.core.data.network.quran.SurahItem
import com.example.quran.core.domain.model.City
import com.example.quran.core.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    @JvmName("mapSurahResponseToDomain")
    fun mapResponseToDomain(input: List<SurahItem>) : Flow<List<Surah>> {
        val listSurah = ArrayList<Surah>()
        input.map {
            val surah = Surah(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation
            )
            listSurah.add(surah)
        }
        return flowOf(listSurah)
    }

    @JvmName("mapQuranEditionItemToDomain")
   fun mapResponseToDomain(input: List<QuranEditionItem>) : Flow<List<QuranEdition>> {
        val quranEditions = ArrayList<QuranEdition>()
        input.map {
            val edition = QuranEdition(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation,
                ayahs = mapAyahsItemToDomain(it.ayahs),
            )
            quranEditions.add(edition)
        }
        return flowOf(quranEditions)
    }

    private fun mapAyahsItemToDomain(input: List<AyahsItem>) : List<Ayah> {
        val listAyah = ArrayList<Ayah>()
        input.map {
            val ayah = Ayah(
                number = it.number,
                text = it.text,
                numberInSurah = it.numberInSurah,
                audio = it.audio
            )
            listAyah.add(ayah)
        }
        return listAyah
    }

    @JvmName("mapCityResponseToDomain")
    fun mapResponseToDomain(input: List<CityItem>): Flow<List<City>> {
        val listCity = ArrayList<City>()
        input.map {
            val city = City(
                location = it.lokasi,
                idCity = it.id
            )
            listCity.add(city)
        }
        return flowOf(listCity)
    }

    @JvmName("mapJadwalItemToDomain")
    fun mapResponseToDomain (input: JadwalItem): Flow<Jadwal> {
        val jadwal = Jadwal(
            date = input.date,
            imsak = input.imsak,
            isya = input.isya,
            subuh = input.subuh,
            dzuhur = input.dzuhur,
            ashar = input.ashar,
            dhuha = input.dhuha,
            terbit = input.terbit,
            tanggal = input.tanggal,
            maghrib = input.maghrib,
        )
        return flowOf(jadwal)
    }
}