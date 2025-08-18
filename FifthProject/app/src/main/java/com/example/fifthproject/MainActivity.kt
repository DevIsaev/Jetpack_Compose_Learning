package com.example.fifthproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.fifthproject.screens.bottom_navigation.BottomNav
import com.example.fifthproject.screens.bottom_navigation.NavGraph
import com.example.fifthproject.ui.theme.FifthProjectTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FifthProjectTheme {
                var navController= rememberNavController()
                Scaffold(bottomBar = {
                    BottomNav(navController)
                }) {
                    NavGraph(navController)
                }
            }
        }
    }
}