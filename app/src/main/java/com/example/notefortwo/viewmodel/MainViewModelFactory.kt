package com.example.notefortwo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider

class FactoryViewModel(context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}