package com.example.notefortwo.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModelProvider
import com.example.notefortwo.data.Purchase
import com.example.notefortwo.ui.theme.NoteForTwoTheme
import com.example.notefortwo.viewmodel.FactoryViewModel
import com.example.notefortwo.viewmodel.MainViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database

const val databaselink = "https://note-14194-default-rtdb.europe-west1.firebasedatabase.app"

class MainActivity : ComponentActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val purchaseList = mutableStateListOf<Purchase>()
        val database = Firebase.database(databaselink)
        val databaseReference = database.getReference("purchase")

        viewModel = ViewModelProvider(this, FactoryViewModel(databaseReference))[MainViewModel::class.java]
        databaseReference.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                purchaseList.clear()
                for (s in snapshot.children) {
                    val purchase = s.getValue(Purchase::class.java)
                    if (purchase != null) {
                        purchaseList.add(purchase)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        setContent {
            NoteForTwoTheme {
                GoogleAuth(this, viewModel)
                NoteColumn(purchaseList, viewModel)
                ClearListButton(viewModel)
            }
        }
    }
}

