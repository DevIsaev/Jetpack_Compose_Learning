package com.example.fifthproject.screens.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

//для отображения экрана
@Composable
fun NavGraph(navHostController: NavHostController) {
    //контроллер переключения и экран по умолчанию
    NavHost(navHostController, startDestination = "экран2"){
        composable("экран1"){
            Screen("все композиции")
        }
        composable("экран2"){
            Screen("избранные")
        }
        composable("экран3"){
            Screen("главный экран")
        }
        composable("экран4"){
            Screen("плейлисты")
        }

    }
}