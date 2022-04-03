package com.example.notefortwo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainViewModel() : ViewModel() {

    fun signWithGoogle(credential: AuthCredential){
        try {
            Firebase.auth.signInWithCredential(credential)
        } catch (e: Exception) {
        }
    }
}