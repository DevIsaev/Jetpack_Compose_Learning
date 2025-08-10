package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.serialization.Contextual
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.screen.MainCard
import com.example.weatherapp.screen.tabLayout
import org.json.JSONObject
const val apiKEY="5b97c6dd45614d44b00152855250508"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                var daysList=remember { mutableStateOf(listOf<WeatherModel>()) }
                apiFun("Novosibirsk",this@MainActivity,daysList)
                Image(
                    painter = painterResource(R.drawable.p2416),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize().alpha(0.6f)
                )
                Column(Modifier.padding(top = 25.dp)) {
                    MainCard()
                    tabLayout(daysList)
                }
                //resultText("Novosibirsk",this@MainActivity)
                //resultText("London",this@MainActivity)
                //resultText("Tokio",this@MainActivity)
            }
        }
    }


    //первая функция- обычный дизайн
    @Composable
    fun resultText(city: String,context: Context){
        var state= remember { mutableStateOf("unknown") }
        Column(Modifier.fillMaxSize().padding(top=50.dp, bottom = 50.dp)){
            Box(Modifier.background(Color.LightGray).fillMaxHeight(0.5f).fillMaxWidth(), contentAlignment = Alignment.Center){//0.5f - занять половину экрана
                Text("Температура в городе $city - ${state.value}")
            }
            Box(Modifier.background(Color.Yellow).fillMaxHeight().fillMaxWidth(), contentAlignment = Alignment.BottomCenter){
                Button(modifier = Modifier.padding(5.dp).fillMaxWidth(), onClick = {
                    getResult(city,context,state)
                }) {
                    Text("refresh")
                }
            }
        }
    }
    //функиця получения температуры через api
    private fun getResult(city: String,context: Context, state: MutableState<String>){
        //ссылка
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$apiKEY&" + //api ключ
                "q=$city" + //город
                "&aqi=no" //нужно ли выводить качество воздуха

        //volley - отправка результата на сервер
        var queue= Volley.newRequestQueue(context)
        //формирование запроса
        var sR= StringRequest(Request.Method.GET,url,{response->
            //взятие элементов из json
            var obj= JSONObject(response)
            //state.value=response
            //взятие из объекта current значение температуры
            state.value=obj.getJSONObject("current").getString("temp_c")
        },{error->
            state.value=error.message.toString()
        })
        queue.add(sR)
    }




    @Composable
    private fun apiFun(city:String,context: Context,dlist: MutableState<List<WeatherModel>>){
        //ссылка
        val url = "https://api.weatherapi.com/v1/forecast.json" +
                "?key=$apiKEY&" + //api ключ
                "q=$city" + //город
                "&days=8&" +
                "aqi=no" +
                "&alerts=no"
        //volley - отправка результата на сервер
        var queue= Volley.newRequestQueue(context)
        //формирование запроса
        var sR= StringRequest(Request.Method.GET,url,{response->
            var list=getWeatherByDay(response)
            dlist.value=list
            Log.d("r",response)
        },{error->
            Log.d("r",error.message.toString())
        })
        queue.add(sR)
    }
    //функиця получения температуры через api по дням, возвращает список
    private fun getWeatherByDay(response:String):List<WeatherModel>{
        if(response.isEmpty()) return listOf()
        var list= ArrayList<WeatherModel>()

        //взятие элементов из json
        var mainObj= JSONObject(response)
        //по дням - forecast {forecastday[...]}
        var days=mainObj.getJSONObject("forecast").getJSONArray("forecastday")
        //город
        var city=mainObj.getJSONObject("location").getString("name")
        //цикл по  массиву  days
        for(i in 0 until days.length()){
            var item=days[i] as JSONObject
            var it=item.getJSONObject("day")
            list.add(
                WeatherModel(city,
                    item.getString("date"),
                    item.getJSONArray("hour").toString(),
                    "",
                    it.getString("mintemp_c"),
                    it.getString("maxtemp_c"),
                    it.getJSONObject("condition").getString("text"),
                    it.getJSONObject("condition").getString("icon")
                )
            )
        }
        //перезапись 1 элемента заполнением температуры
        list[0]=list[0].copy(time = mainObj.getJSONObject("current").getString("last_updated"),
            currentTemp = mainObj.getJSONObject("current").getString("temp_c"))
        return list
    }
}

