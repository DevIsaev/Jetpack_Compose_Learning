package com.example.fourthproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun itemCard(item: Human) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp).background(Color.White)) {
        Image(painter = painterResource(id= item.imgID),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier.padding(9.dp).size(64.dp).clip(CircleShape))
        Text(text=item.title)
    }
}

@Composable
fun itemCard2(item: Human2) {
    //переменная чтобы спрятать текст
    var isExPANDED by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(5.dp).background(Color.White).fillMaxWidth()) {
        Image(painter = painterResource(id= item.imgID),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier.padding(9.dp).size(64.dp).clip(CircleShape))
        Column(Modifier.padding(top=5.dp)) {
            Text(text=item.title)
            Text(text=item.content,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp, modifier = Modifier.clickable{
                    //проивоположное значение
                    isExPANDED=!isExPANDED
                }, maxLines = if(isExPANDED)10 else 1)
        }
    }
}