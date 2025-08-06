package com.example.weatherapp

import android.content.Context
import android.os.Bundle
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
import com.example.weatherapp.screen.MainScreen
import org.json.JSONObject
const val apiKEY="5b97c6dd45614d44b00152855250508"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                MainScreen()
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

    //продвинутый дизайн
}

