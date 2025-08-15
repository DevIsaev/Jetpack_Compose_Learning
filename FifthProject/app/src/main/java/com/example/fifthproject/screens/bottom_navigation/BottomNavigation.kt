package com.example.fifthproject.screens.bottom_navigation

import androidx.compose.foundation.background
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
import androidx.navigation.compose.currentBackStackEntryAsState

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