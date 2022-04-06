package com.example.notefortwo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notefortwo.ui.theme.Aquamarine
import com.example.notefortwo.viewmodel.MainViewModel

@Composable
fun ClearListButton(viewModel: MainViewModel){

    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {
                openDialog.value = true
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Aquamarine
            ),
            shape = CircleShape,
            modifier = Modifier
                .padding(10.dp)
                .shadow(5.dp, CircleShape, true)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Black)
        }
    }

    if (openDialog.value) {
        AlertDialog(
            backgroundColor = Color.White,
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "    Are you sure? \n")
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 6.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { openDialog.value = false },
                        modifier = Modifier.padding(end = 3.dp)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            openDialog.value = false
                            viewModel.clearList()
                        },
                        modifier = Modifier.padding(start = 3.dp)
                    ) {
                        Text("Delete")
                    }
                }
            }
        )
    }
}
