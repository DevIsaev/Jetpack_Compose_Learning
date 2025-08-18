package com.example.fifthproject.screens.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fifthproject.R


//класс элемента у bottom navigation
sealed class BottomItem(var title:String,var icon:Int,var route: String) {

    object Screen1: BottomItem("все композиции",R.drawable.list,"экран1")
    object Screen2: BottomItem("избранные",R.drawable.fav,"экран2")
    object Screen3: BottomItem("главный экран",R.drawable.base,"экран3")
    object Screen4: BottomItem("плейлисты",R.drawable.playlist,"экран4")
}

@Composable
fun Screen(title: String) {
    Text("это экран ${title}", Modifier.fillMaxSize().wrapContentHeight(), textAlign = TextAlign.Center)
}

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

@Composable
fun BottomNav(navController: NavController) {
    //список элементов экранов
    var listItems=listOf(
        BottomItem.Screen1,BottomItem.Screen2,BottomItem.Screen3,BottomItem.Screen4
    )
            //вызов bottomnavigation
    NavigationBar(Modifier.background(color = Color.Blue)){
        //переключатели
        val backStackEntry by navController.currentBackStackEntryAsState()
        var currentRout=backStackEntry?.destination?.route

        //цикл по всем элементам списка экрана
        listItems.forEach { item ->
            NavigationBarItem(currentRout==item.route,  {
                //навигация по route
                navController.navigate(item.route)
            }, icon = { Icon(painterResource(item.icon), "") },
                label = {
                    Text(item.title, fontSize = 9.sp, textAlign = TextAlign.Center)
                },colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Red, unselectedIconColor = Color.Gray) )
        }
    }
}