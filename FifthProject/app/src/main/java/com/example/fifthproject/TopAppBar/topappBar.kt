package com.example.fifthproject.TopAppBar

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(context: Context) {
    TopAppBar(
        title = { Text("TopAppBar") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context,"Menu",Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context,"Home",Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
            }
            IconButton(onClick = {
                Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
            }
        }
    )
}
