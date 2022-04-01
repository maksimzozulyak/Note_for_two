package com.example.notefortwo.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notefortwo.ui.theme.*

@Composable
fun NoteColumn(list: List<String>){
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
    ) {
        items(items = list) { it ->
            PurchaseTextField(name = it)
        }
    }
}

@Composable
fun PurchaseTextField(name: String) {
    var saved by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("Hello $name") }
    var isDone by remember { mutableStateOf(false) }
    val buttonWeight by animateDpAsState(
        if (saved) 0.dp else 76.dp
    )

    Row(modifier = Modifier.heightIn(min = 72.dp)) {
        TextButton(onClick = { isDone = !isDone},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Aquamarine
            ),

            shape = CircleShape,
            modifier = Modifier
                .padding(start = 7.dp, end = 4.dp, top = 11.dp, bottom = 11.dp)
                .fillMaxHeight()
                .width(42.dp)
                .shadow(
                    shape = CircleShape,
                    elevation = 8.dp,
                    clip = true
                )
        ) {
            Crossfade(targetState = isDone) { done ->
                if (done) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.Black)
                } else {
                    Icon(imageVector = Icons.Default.Done, contentDescription = null, tint = Color.Black)
                }
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .shadow(
                    shape = RoundedCornerShape(10.dp),
                    elevation = 10.dp,
                    clip = true
                ),
            color = Gray,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row {
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        saved = false
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                OutlinedButton(
                    modifier = Modifier
                        .padding(top = 4.dp, end = 8.dp, bottom = 4.dp)
                        .width(buttonWeight)
                        .height(46.dp),
                    onClick = { saved = true },
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Blue,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Save",
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteForTwoTheme {
        NoteColumn(listOf("1","2","3"))
    }
}