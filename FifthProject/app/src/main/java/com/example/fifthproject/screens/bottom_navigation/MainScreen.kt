package com.example.fifthproject.screens.bottom_navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    var navController= rememberNavController()
    Scaffold(bottomBar = {
        BottomNav(navController)
    }) {
        NavGraph(navController)
    }
}