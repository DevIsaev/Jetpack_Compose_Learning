package com.example.navdrawermaterial3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navdrawermaterial3.ui.theme.NavDrawerMaterial3Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavDrawerMaterial3Theme {
                val navController = rememberNavController()
                val navController2 = rememberNavController()
                var drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
                var coroutineScope= rememberCoroutineScope()

                Drawer(
                    navController = navController,
                    drawerState = drawerState,
                    coroutineScope = coroutineScope
                ) {

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
                                onClickToScreen1 = {
                                    navController.navigate("screen1") {
                                        popUpTo("screen1") { inclusive = true }
                                    }
                                },
                                onClickToScreen2 = { navController.navigate("screen2") },
                                openDrawer = { coroutineScope.launch { drawerState.open() } }
                            )
                        }
                    }
                }
            }
        }
    }
}