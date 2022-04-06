package com.example.notefortwo.ui

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.notefortwo.data.Purchase
import com.example.notefortwo.ui.theme.*
import com.example.notefortwo.viewmodel.FactoryViewModel
import com.example.notefortwo.viewmodel.MainViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun NoteColumn(viewModel: MainViewModel){

    val purchaseList = remember {mutableStateListOf<Purchase>()}
    val database = Firebase.database(databaselink)
    val databaseReference = database.getReference("purchase")
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

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
    ) {
        items(items = purchaseList) { it ->
            PurchaseTextField(purchase = it, index = purchaseList.indexOf(it), lastIndex = purchaseList.size-1, viewModel)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PurchaseTextField(purchase: Purchase, index: Int, lastIndex: Int, viewModel: MainViewModel) {

    val database = Firebase.database(databaselink)
    val keyboardController = LocalSoftwareKeyboardController.current
    val databaseRef = database.getReference("purchase")
    var saved by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf(purchase.field) }
    var isDone by remember { mutableStateOf(purchase.isDone) }
    val buttonWeight by animateDpAsState(
        if (saved) 0.dp else 76.dp
    )
    if (purchase.field == "" && text == ""){
        text = ""
    }

    Row(modifier = Modifier.heightIn(min = 72.dp)) {
        TextButton(onClick = { isDone = !isDone
                databaseRef.child("$index/done").setValue(
                isDone)
                             },
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
                        .fillMaxHeight(),
                    textStyle =
                        if (isDone) TextStyle(
                            fontSize = 20.sp,
                            textDecoration = TextDecoration.LineThrough
                        ) else TextStyle(
                            fontSize = 20.sp
                        )
                )
                OutlinedButton(
                    modifier = Modifier
                        .padding(top = 4.dp, end = 8.dp, bottom = 4.dp)
                        .width(buttonWeight)
                        .height(46.dp),
                    onClick = { saved = true
                        viewModel.addPurchase(index,text)
                        if (index == lastIndex) {
                            viewModel.addEmptyField(index)
                        }
                        keyboardController?.hide()     },
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
