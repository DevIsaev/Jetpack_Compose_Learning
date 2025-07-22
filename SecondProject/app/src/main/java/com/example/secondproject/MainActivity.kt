package com.example.secondproject

import android.health.connect.datatypes.ElevationGainedRecord
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.secondproject.ui.theme.SecondProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondProjectTheme {
                Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                    listItem("Bob",true)
                }
            }
        }
    }
}

//функция элемента из списка
@Composable
fun listItem(name:String,status:Boolean){
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp).pointerInput(UInt){

        detectDragGesturesAfterLongPress { change, dragAmount ->          Log.d("log","drag after long press $dragAmount")}
    },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
        Box(modifier = Modifier.fillMaxWidth().background(Color.Green).padding(10.dp), contentAlignment = Alignment.TopStart
        ){

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(Color.Red)) {
                Image(painter = painterResource(id=R.drawable.p1),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    contentDescription = "")
                Column (Modifier.padding(horizontal = 25.dp)){
                    Text(text = name)
                    Text(text = status.toString())
                }
            }
        }
    }
}

