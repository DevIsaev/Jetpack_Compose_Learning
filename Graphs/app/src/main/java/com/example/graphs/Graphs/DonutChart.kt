package com.example.graphs.Graphs

import android.graphics.Typeface
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun DonutChartGraph(){
    var color=remember{ mutableStateOf(Color.Black) }
    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
            PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
            PieChartData.Slice("Lenovo", 40f, Color(0xFFEC9F05)),
            PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
        ),
        plotType = PlotType.Donut
    )
    val donutChartConfig = PieChartConfig(
        labelVisible = true,
       labelFontSize = 42.sp,
        strokeWidth = 120f,
        labelColor = color.value,
        activeSliceAlpha = 9f,
        isAnimationEnable = true,
    )
    DonutPieChart(modifier = Modifier.fillMaxWidth().height(500.dp), donutChartData, donutChartConfig,
        onSliceClick = {
            color.value=it.color
        }
    )
}