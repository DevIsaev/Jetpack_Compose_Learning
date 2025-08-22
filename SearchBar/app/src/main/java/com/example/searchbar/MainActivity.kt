package com.example.searchbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.searchbar.ui.theme.SearchBarTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var searchQuery=remember{ mutableStateOf("") }
            var b=remember { mutableStateOf(false) }
            //поулчение обьекта и списка
            var mainList=remember{ mutableStateOf(listNames.list) }
            SearchBarTheme {
                Column(Modifier.fillMaxSize()) {
                    SearchBar(
                        searchQuery.value,
                        onQueryChange = { text ->
                            searchQuery.value = text
                            mainList.value= listNames.search(text)
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = SearchBarDefaults.colors(
                            containerColor = Color.Yellow,
                            inputFieldColors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Red,
                                focusedContainerColor = Color.Blue
                            )
                        ),
                        onSearch = { text ->
                            //обновление списка при вводе и поиске
                            mainList.value= listNames.search(text)
                        },
                        placeholder = {
                            Text("поиск", color = Color.Black)
                        },
                        active = b.value,
                        onActiveChange = {
                            b.value = it
                        })
                    {
//                        Text("hello")
                        LazyColumn(Modifier.fillMaxWidth()){
                            items(mainList.value){
                                Box(Modifier.fillMaxWidth().padding(10.dp).clickable{
                                    mainList.value= listNames.search(it)
                                    b.value=false
                                }, contentAlignment = Alignment.Center){
                                    Text(it)
                                }
                            }
                        }
                    }
                    LazyColumn(Modifier.fillMaxWidth()){
                        items(mainList.value){
                            Box(Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center){
                                Text(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
object listNames{
    var list = listOf(
        "Алексей", "Sarah", "Дарья", "Daniel", "Кирилл",
        "Emily", "Артем", "Thomas", "Виктория", "Christopher",
        "Полина", "Matthew", "Никита", "Jessica", "Марк"
    )
    fun search(text: String):List<String>{
        return list.filter {
            //поиск совпадения с текстом
            it.lowercase().startsWith(text.lowercase())
        }
    }
}