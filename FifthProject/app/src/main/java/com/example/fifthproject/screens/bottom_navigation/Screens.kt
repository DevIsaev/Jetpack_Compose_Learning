package com.example.fifthproject.screens.bottom_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Screen(title: String) {
    Text("это экран ${title}", Modifier.fillMaxSize().wrapContentHeight(), textAlign = TextAlign.Center)
}
