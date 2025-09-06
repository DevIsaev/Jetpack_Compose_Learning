package com.example.constrainlayoutexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.constrainlayoutexample.ui.theme.ConstrainLayoutExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstrainLayoutExampleTheme {
                prevConstLay()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun prevConstLay(){
    ConstraintLayout(modifier = Modifier.fillMaxSize().background(Color.LightGray)){
        //референсы для привязки
        var (text,button,image)=createRefs()
        //отступ на 20 процентов от низа
        var bottomGuideLine=createGuidelineFromBottom(0.2f)
        //кнопка
        Button({

        }, modifier = Modifier.constrainAs(button){//название референса
                //расположения(top,bottom,start,end)
            //внизу и с конца
//            bottom.linkTo(parent.bottom,16.dp)
//            end.linkTo(parent.end,5.dp)
            //посредине
            bottom.linkTo(bottomGuideLine)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
            top.linkTo(bottomGuideLine)
        }) {
            Text("click!")
        }
        Text("text", modifier = Modifier.constrainAs(text){
            //текст выше кнопки
            bottom.linkTo(button.bottom,65.dp)
            end.linkTo(button.end)
            start.linkTo(button.start)
        })
        Image(painter = painterResource(R.drawable.logo7),"", Modifier.constrainAs(image){
            //изображение выше кнопки и текста
            bottom.linkTo(text.bottom,45.dp)
            end.linkTo(button.end)
            start.linkTo(button.start)
        })
    }
}