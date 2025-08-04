package com.example.fourthproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fourthproject.ui.theme.FourthProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FourthProjectTheme {
//                lazycolumnExample()
//                lazyrowExample()
                Column(Modifier.padding(15.dp)) {
                    lazyrowPeople()
                    lazycolumnPeople()
                }
            }
        }
    }
    //обычный column - элементы, которые не видно, все еще хранятся в памяти
    @Composable
    fun columnExample(){
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().verticalScroll(
            rememberScrollState()
        )) {
            for(i in 0..100)
                Text("retrewf $i", fontSize = 30.sp, modifier = Modifier.padding(vertical = 10.dp))
        }
    }

    //lazycolumn - в память загружаются только те элементы, которые видно на экране
    @Composable
    fun lazycolumnExample(){
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {

            //вручную
//            items(count = 100){
//                Text("retrewf $it", fontSize = 30.sp, modifier = Modifier.padding(vertical = 10.dp))
//            }
            //по индексу(дан список, взять элементы из списка)
            itemsIndexed(listOf("item1","item2","ewrew","rwerw","werwqweq","rwerwer","rwerwr")){
               index,item->  Text("$index retrewf $item ", fontSize = 30.sp, modifier = Modifier.padding(vertical = 10.dp))
            }
        }
    }
    @Composable
    fun lazyrowExample(){
        LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {

            //вручную
//            items(count = 100){
//                Text("retrewf $it", fontSize = 30.sp, modifier = Modifier.padding(vertical = 10.dp))
//            }
            //по индексу(дан список, взять элементы из списка)
            itemsIndexed(listOf("item1","item2","ewrew","rwerw","werwqweq","rwerwer","rwerwr")){
                    index,item->  Text("$index retrewf $item ", fontSize = 30.sp, modifier = Modifier.padding(horizontal = 10.dp))
            }
        }
    }

    @Composable
    fun lazyrowPeople(){
        LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp).background(Color.Gray)) {
            itemsIndexed(listOf(
                Human(R.drawable.p6754,"item1"),
                Human(R.drawable.p676r6,"item2"),
                Human(R.drawable.p78575,"item3"),
                Human(R.drawable.p8775675,"item4"),
                Human(R.drawable.p45353466,"item5"),
                Human(R.drawable.p87767556577,"item6"),)){
                _,i-> itemCard(i)
            }
        }
    }

    @Composable
    fun lazycolumnPeople(){
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp).background(Color.Gray)) {
            itemsIndexed(listOf(
                Human2(R.drawable.p6754,"item1","teirgiergiergmergmimigme1r2wgefwergserewrgrherhthbetnthbdfrgsrt"),
                Human2(R.drawable.p676r6,"item2","teirgiergiergmergmimigme1r2wgefwergserewrgrherhthbetnthbdfrgsrt"),
                Human2(R.drawable.p78575,"item3","teirgiergiergmergmimigme1r2wgefwergserewrgrherhthbetnthbdfrgsrt"),
                Human2(R.drawable.p8775675,"item4","teirgiergiergmergmimigme1r2wgefwergserewrgrherhthbetnthbdfrgsrt"),
                Human2(R.drawable.p45353466,"item5","teirgiergiergmergmimigme1r2wgefwergserewrgrherhthbetnthbdfrgsrt"),
                Human2(R.drawable.p87767556577,"item6","teirgiergiergmergmimigme1r2wgefwergserewrgrherhthbetnthbdfrgsrt"),)){
                    _,i-> itemCard2(i)
            }
        }
    }
}

