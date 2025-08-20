package com.example.navdrawermaterial3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    content: @Composable () -> Unit
) {
    val items = listOf(
        DrawerItem(R.drawable.mess, "Message", "screen1"),
        DrawerItem(R.drawable.loc, "Location", "screen2"),
        DrawerItem(R.drawable.pass, "Pass", "screen3")
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painterResource(R.drawable.background),
                    "",
                    Modifier.fillMaxWidth().height(70.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(15.dp))

                items.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.title) },
                        icon = { Icon(painterResource(item.img), "") },
                        selected = currentRoute == item.route,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                                // Навигация к выбранному экрану
                                navController.navigate(item.route) {
                                    // Очистка стека навигации до корневого элемента
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Запуск одного экземпляра destination
                                    launchSingleTop = true
                                    // Восстановление состояния при повторной навигации
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        },
        content = content
    )
}

data class DrawerItem(val img: Int, val title: String, val route: String)