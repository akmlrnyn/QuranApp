package com.example.quran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.databinding.ItemSurahBinding
import com.example.quran.network.SurahItem

class QuranAdapter : RecyclerView.Adapter<QuranAdapter.MyViewHolder>() {
    private val listSurah = ArrayList<SurahItem>()

    fun setData(list: List<SurahItem>?) {
        if (list == null) return
        listSurah.clear()
        listSurah.addAll(list)
    }

    class MyViewHolder(val binding: ItemSurahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
       ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: QuranAdapter.MyViewHolder, position: Int) {
        val data = listSurah[position]
        holder.binding.apply {
            tvNumber.text = data.number.toString()
            tvName.text = data.name
            tvSurah.text = data.englishName
            val revelation = data.revelationType
            val numberOfAyah = data.numberOfAyahs
            val resultOfAyah = "$revelation - $numberOfAyah Ayahs"
            tvAyah.text = resultOfAyah
        }
    }

    override fun getItemCount() = listSurah.size

}