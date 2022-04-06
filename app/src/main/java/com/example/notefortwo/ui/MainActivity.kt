package com.example.notefortwo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.notefortwo.ui.theme.NoteForTwoTheme
import com.example.notefortwo.viewmodel.FactoryViewModel
import com.example.notefortwo.viewmodel.MainViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val databaselink = "https://note-14194-default-rtdb.europe-west1.firebasedatabase.app"

class MainActivity : ComponentActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Firebase.database(databaselink)
        val databaseReference = database.getReference("purchase")

        viewModel = ViewModelProvider(this, FactoryViewModel(databaseReference))[MainViewModel::class.java]

        setContent {
            NoteForTwoTheme {
                GoogleAuth(this, viewModel)
                NoteColumn(viewModel)
                ClearListButton(viewModel)
            }
        }
    }
}

