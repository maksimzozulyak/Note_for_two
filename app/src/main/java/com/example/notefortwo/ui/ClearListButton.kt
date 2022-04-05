package com.example.notefortwo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notefortwo.ui.theme.Aquamarine
import com.example.notefortwo.viewmodel.MainViewModel

@Composable
fun ClearListButton(viewModel: MainViewModel){
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {
                viewModel.clearList()
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
}