package com.example.thirdproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdproject.ui.theme.ThirdProjectTheme
import com.example.thirdproject.R // Правильный импорт ресурсов

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirdProjectTheme {
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    listItem("Jane",true,R.drawable.p1)
                    listItem("Bob",true,R.drawable.p2)
                    listItem("Jim",true,R.drawable.p3)
                    listItem("Jane",true,R.drawable.p1)
                    listItem("Bob",true,R.drawable.p2)
                    listItem("Jim",true,R.drawable.p3)
                    listItem("Jane",true,R.drawable.p1)
                    listItem("Bob",true,R.drawable.p2)
                    listItem("Jim",true,R.drawable.p3)
                    listItem("Jane",true,R.drawable.p1)
                    listItem("Bob",true,R.drawable.p2)
                    listItem("Jim",true,R.drawable.p3)
                    listItem("Jane",true,R.drawable.p1)
                    listItem("Bob",true,R.drawable.p2)
                    listItem("Jim",true,R.drawable.p3)
                }
                //
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    buttonState()
                }
            }
        }
    }

    @Composable
    fun listItem(name: String,status:Boolean,im:Int){
        //var count=0
        //для отслеживания состояния
        var count= remember{
            mutableStateOf(0)
        }
        Card(modifier = Modifier.fillMaxWidth().padding(15.dp).
        clickable {
            //count++
            //состояние
            count.value++
            Toast.makeText(this, count.value.toString(), Toast.LENGTH_SHORT).show()
        },
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {

            Box(modifier = Modifier.fillMaxWidth().background(Color.Green).padding(10.dp), contentAlignment = Alignment.TopStart){
                Row(verticalAlignment = Alignment.CenterVertically,) {
                    Image(painter = painterResource(id=im),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentDescription = "")
                    Column (Modifier.padding(horizontal = 25.dp)){
                        Text(text = name)
                        Text(text = count.value.toString())
                    }
                }
            }
        }
    }

    @Composable
    fun buttonState(){
        var counter= remember {mutableStateOf(0)}
        var color= remember {mutableStateOf(Color.Green)}

        Box(modifier = Modifier.background(color.value, shape = CircleShape).size(100.dp).clickable {
            counter.value++
        }, contentAlignment = Alignment.Center){
            Text(counter.value.toString(), style = TextStyle(Color.White, fontSize = 20.sp))
                when(counter.value){
                    10->{
                        color.value=Color.Red
                    }
                    20->{
                        color.value=Color.Blue
                    }
                    30->{
                        color.value=Color.Cyan
                    }
                }
        }

    }
}

