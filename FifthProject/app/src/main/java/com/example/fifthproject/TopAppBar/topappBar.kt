package com.example.fifthproject.TopAppBar

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.fifthproject.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(context: Context,
              coroutineScope: CoroutineScope, // Принимаем scope как параметр
              snackbarHostState: SnackbarHostState, // Принимаем состояние снэкбара
              drawerState: DrawerState
) {
    TopAppBar(
        title = { Text("TopAppBar") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    //вызов snackbar
                    snackbarHostState.showSnackbar("Home clicked!")
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            IconButton(onClick = {
                coroutineScope.launch {
                    var result=snackbarHostState.showSnackbar("удаление", actionLabel = "отменить")
                    //если поизошло нажатие на actionlabel
                    if (result== SnackbarResult.ActionPerformed){
                        Toast.makeText(context,"отменено",Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)
            }
        }
    )
}
