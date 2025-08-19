package com.example.sixthproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sixthproject.ui.theme.SixthProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SixthProjectTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "screen1"
                ) {
                    composable("screen1") {
                        Screen1(
                            onClickToScreen2 = { navController.navigate("screen2") },
                            onClickToScreen3 = { navController.navigate("screen3") }
                        )
                    }
                    composable("screen2") {
                        Screen2(
                            onClickToScreen1 = { navController.navigate("screen1") },
                            onClickToScreen3 = { navController.navigate("screen3") }
                        )
                    }
                    composable("screen3") {
                        Screen3(
                            onClickToScreen1 = { navController.navigate("screen1"){
                                //убрать из памяти предыдущие экраны
                                popUpTo("screen1"){
                                    //просто выйти из приложения
                                    inclusive=true
                                }
                            } },
                            onClickToScreen2 = { navController.navigate("screen2") }
                        )
                    }
                }
            }
        }
    }
}