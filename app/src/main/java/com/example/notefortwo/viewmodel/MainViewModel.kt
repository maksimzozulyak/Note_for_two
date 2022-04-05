package com.example.notefortwo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notefortwo.data.Purchase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainViewModel(private val databaseRef: DatabaseReference) : ViewModel() {

    fun signWithGoogle(credential: AuthCredential){
        try {
            Firebase.auth.signInWithCredential(credential)
        } catch (e: Exception) {
        }
    }

    fun addPurchase(index: Int, text: String){
        databaseRef.child("$index").setValue(
            Purchase(text)
        )
    }

    fun addEmptyField(index: Int){
        databaseRef.child("${index + 1}").setValue(
            Purchase("")
        )
    }

    fun clearList(){
        databaseRef.removeValue()
        databaseRef.child("0").setValue(
            Purchase("")
        )
    }
}