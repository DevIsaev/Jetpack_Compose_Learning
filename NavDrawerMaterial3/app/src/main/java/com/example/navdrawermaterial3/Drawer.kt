package com.example.navdrawermaterial3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
import kotlinx.coroutines.launch

@Composable
fun Drawer() {
var items=listOf(DrawerItem(R.drawable.mess,"Message"),DrawerItem(R.drawable.loc,"Location"),DrawerItem(R.drawable.pass,"Pass"))

    var drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    var coroutineScope= rememberCoroutineScope()
    var selectedItem=remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(drawerState = drawerState, drawerContent ={
        ModalDrawerSheet {
            //header
            Image(painterResource(R.drawable.background),"", Modifier.fillMaxWidth().height(70.dp), contentScale = ContentScale.Crop)
            Spacer(Modifier.height(15.dp))
            //items
            items.forEach { item ->
                NavigationDrawerItem({
                    Text(item.title)
                }, icon = {
                    Icon(painterResource(item.img),"")
                }, selected = selectedItem.value==item, onClick = {
                    coroutineScope.launch {
                        selectedItem.value=item
                        drawerState.close()
                    }
                })
            }
        }
    }, content = {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Button(onClick = {
                coroutineScope.launch { drawerState.open() }
            }) {
                Text("drawer")
            }
        }
    } )
}

data class DrawerItem(var img: Int,var title: String)