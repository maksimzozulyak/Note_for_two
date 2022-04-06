package com.example.notefortwo.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference

class FactoryViewModel(private val databaseRef: DatabaseReference): ViewModelProvider.NewInstanceFactory() {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(databaseRef) as T
    }
}