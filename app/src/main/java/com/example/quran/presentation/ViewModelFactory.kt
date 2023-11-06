package com.example.quran.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.quran.di.Injection
import com.example.quran.presentation.quran.QuranViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        when {
            modelClass.isAssignableFrom(QuranViewModel::class.java) -> {
                QuranViewModel(Injection.provideQuranRepository()) as T
            }
            else -> throw Throwable("Unknown Viewmodel Class: " + modelClass.name)
        }

}