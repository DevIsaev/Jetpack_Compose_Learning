package com.example.firstproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstproject.ui.theme.FirstProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstProjectTheme {
                Column() {
                    exColumn(Color.Blue)
                    exRow(Color.Red)
                    Row(modifier = Modifier.background(Color.Gray).fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Column(modifier = Modifier.background(Color.Green).fillMaxHeight().fillMaxWidth(0.5f),
                            verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                            NewText("fun")
                            NewText("fun1")
                            NewText("fun2")
                        }
                        exColumn2(Color.DarkGray)
                    }
                }
            }
        }
    }
}


@Composable
fun NewText(text: String){
    Text(text = text)
}

//контейнер Column
@Composable
fun exColumn(Color:Color){
    Column(modifier = Modifier.background(Color).fillMaxWidth(0.5f),
        verticalArrangement = Arrangement.SpaceAround) {
        NewText("fun")
        NewText("fun1")
        NewText("fun2")
    }
}

@Composable
fun exColumn2(Color:Color){
    Column(modifier = Modifier.background(Color).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
        NewText("fun")
        NewText("fun1")
        NewText("fun2")
    }
}

//контейнер Row
@Composable
fun exRow(Color:Color){
    Row (modifier = Modifier.background(Color),
        horizontalArrangement = Arrangement.SpaceAround) {
        NewText("fun")
        NewText("fun1")
        NewText("fun2")
    }
}