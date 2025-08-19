package com.example.fifthproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fifthproject.TopAppBar.topAppBar
import com.example.fifthproject.drawer.DrawerBody
import com.example.fifthproject.drawer.DrawerNavigation
import com.example.fifthproject.screens.bottom_navigation.BottomNav
import com.example.fifthproject.screens.bottom_navigation.NavGraph
import com.example.fifthproject.ui.theme.FifthProjectTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FifthProjectTheme {
                // SnackbarHostState вместо ScaffoldState
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                var c= remember { Color.Blue }
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        // Содержимое вашего Drawer
                        Column(Modifier.padding(end=30.dp)) {
                            DrawerNavigation(c)
                            DrawerBody()
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = { BottomNav(navController) },
                        topBar = { topAppBar(this, coroutineScope, snackbarHostState,drawerState) },
                        snackbarHost = {
                            SnackbarHost(snackbarHostState) { data ->
                                Snackbar(
                                    data,
                                    containerColor = Color.Blue,
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier.padding(bottom = 25.dp)
                                )
                            }
                        },
                        ) {
                        NavGraph(navController)
                    }
                }
            }
        }
    }
}