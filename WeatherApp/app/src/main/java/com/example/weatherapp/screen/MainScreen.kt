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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.example.weatherapp.data.WeatherModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Preview(showBackground = false)
@Composable
fun MainCard() {
    val currentDateTime = LocalDateTime.now()
    Column(Modifier.padding(15.dp)) {
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


@Composable
fun tabLayout(dataList: MutableState<List<WeatherModel>>){
    //список с названиями
    var tabList=listOf("HOURS","DAYS")
    var pagerState = rememberPagerState { tabList.size }
    var tabIndex=pagerState.currentPage
    var corutineSCop= rememberCoroutineScope()

    Column(modifier = Modifier.padding(5.dp).clip(RoundedCornerShape(15.dp))) {
        TabRow(selectedTabIndex = tabIndex, containerColor = blueLight, contentColor = Color.White,
                indicator = { position ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(position[tabIndex]),
                        height = 4.dp, color = blueLight)
                }){

            //создание табов по списку
            tabList.forEachIndexed { index, text ->
                Tab(selected = false, onClick = {
                    //анимация перелистывания
                    corutineSCop.launch { pagerState.animateScrollToPage(index)

                    }
                },
                    text={
                        Text(text=text)
                    })
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                //заполнение элементами
//                items(count=15) {
//                    itemList()
//                }
                //список с элементами, по дням или часам
                itemsIndexed(dataList.value) {_,item-> itemList(item) }
            }
        }
    }
}

