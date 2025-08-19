package com.example.fifthproject.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifthproject.MainActivity

@Composable
fun DrawerNavigation(c: Color) {
    Column(Modifier.fillMaxWidth().height(170.dp).background(c), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Header", fontSize = 20.sp)
    }
}

@Composable
fun DrawerBody(){
    LazyColumn(Modifier.fillMaxWidth().background(Color.LightGray),) {
        items(10){
            Text("item ${it+1}", Modifier.fillMaxWidth().clickable{
                
            }.padding(20.dp))
        }
    }
}