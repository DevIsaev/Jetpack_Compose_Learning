package com.example.weatherapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.ui.theme.blueLight


@Composable
fun MainList(list: List<WeatherModel>,currDays: MutableState<WeatherModel>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        //заполнение элементами
//                items(count=15) {
//                    itemList()
//                }
        //список с элементами, по дням или часам
        itemsIndexed(list) {_,item-> itemList(item,currDays) }
    }
}

@Composable
fun itemList(item: WeatherModel,currentDay: MutableState<WeatherModel>) {
    Card(modifier = Modifier.fillMaxWidth().padding(top=3.dp).clickable{
        if(item.hours.isEmpty()){
            return@clickable
        }
        currentDay.value=item
    },
        colors = CardDefaults.cardColors(containerColor = blueLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), shape = RoundedCornerShape(5.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 5.dp)) {
                Text(item.time, )
                Text(item.condition,color = Color.White)
            }
            Text(item.currentTemp.ifEmpty { "${item.max}/${item.min}" }, color = Color.White,style = TextStyle(fontSize = 25.sp))
            AsyncImage(model = "https:${item.icon}",contentDescription = "im2",  modifier = Modifier.size(35.dp).padding(end = 8.dp) )
        }
    }
}


@Composable
fun SearchDialog(dialogState: MutableState<Boolean>,onSubmit:(String)-> Unit){
    var dialogText=remember { mutableStateOf("") }
    AlertDialog({
        dialogState.value=false
    }, title = {
        Column(Modifier.fillMaxWidth()) {
            Text("City?")
            TextField(value = dialogText.value, onValueChange = {
                dialogText.value=it
            })
        } },
        confirmButton = {
        //кнопка соглашения
        TextButton({
            onSubmit(dialogText.value)
            dialogState.value=false
        }) {
            Text("OK")
        }
    }, dismissButton = { TextButton({
            dialogState.value=false
        }) {
            Text("Cancel")
        }
    })
}