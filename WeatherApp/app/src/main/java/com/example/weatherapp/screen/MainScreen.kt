package com.example.weatherapp.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.blueLight
import coil.compose.AsyncImage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val currentDateTime = LocalDateTime.now()
    Image(
        painter = painterResource(R.drawable.p2416),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize().alpha(0.6f)
    )
    Column(Modifier.fillMaxSize().padding(15.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = blueLight),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString(),
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )
                    AsyncImage(model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "im2",  modifier = Modifier.size(35.dp).padding(top = 3.dp, end = 8.dp))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Madrid",
                        style = TextStyle(fontSize = 25.sp),
                        color = Color.White
                    )
                    Text(
                        "23*C",
                        modifier = Modifier.padding(5.dp),
                        style = TextStyle(fontSize = 65.sp),
                        color = Color.White
                    )
                    Text(
                        "Cloudy",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )


                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        IconButton(onClick = {}){ Icon(painter = painterResource(R.drawable.search), contentDescription = "",tint = Color.White) }

                        Text(
                            "27*/9*",
                            style = TextStyle(fontSize = 10.sp),
                            color = Color.White
                        )

                        IconButton(onClick = {}){ Icon(painter = painterResource(R.drawable.sync), contentDescription = "", tint = Color.White) }
                    }
                }
            }
        }
    }
}