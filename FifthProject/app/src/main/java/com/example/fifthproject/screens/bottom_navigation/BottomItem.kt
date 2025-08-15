package com.example.fifthproject.screens.bottom_navigation

import com.example.fifthproject.R

//класс элемента у bottom navigation
sealed class BottomItem(var title:String,var icon:Int,var route: String) {

    object Screen1: BottomItem("все композиции",R.drawable.list,"экран1")
    object Screen2: BottomItem("избранные",R.drawable.fav,"экран2")
    object Screen3: BottomItem("главный экран",R.drawable.base,"экран3")
    object Screen4: BottomItem("плейлисты",R.drawable.playlist,"экран4")
}
