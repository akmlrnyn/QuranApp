package com.example.quran.utils

import com.example.quran.core.domain.model.Ayah
import com.example.quran.core.domain.model.QuranEdition
import com.example.quran.core.domain.model.Surah
import com.example.quran.core.network.quran.AyahsItem
import com.example.quran.core.network.quran.QuranEditionItem
import com.example.quran.core.network.quran.SurahItem
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
}