package com.example.notefortwo.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.notefortwo.R
import com.example.notefortwo.ui.NoteColumn
import com.example.notefortwo.ui.theme.NoteForTwoTheme
import com.example.notefortwo.viewmodel.FactoryViewModel
import com.example.notefortwo.viewmodel.MainViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database

const val databaselink = "https://note-14194-default-rtdb.europe-west1.firebasedatabase.app"

class MainActivity : ComponentActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, FactoryViewModel(this))[MainViewModel::class.java]
        val database = Firebase.database(databaselink)
        setContent {
            GoogleAuth(this, viewModel)
            NoteForTwoTheme {
                NoteColumn(listOf("1","2","3"))
            }
        }
    }
}

