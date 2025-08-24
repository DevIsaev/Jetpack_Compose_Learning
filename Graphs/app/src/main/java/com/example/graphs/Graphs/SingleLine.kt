package com.example.graphs.Graphs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import kotlin.random.Random

@Composable
fun getPoint(): ArrayList<co.yml.charts.common.model.Point> {
    var listP= ArrayList<co.yml.charts.common.model.Point>()
    //за месяц
    for (i in 0..31){
        listP.add(co.yml.charts.common.model.Point(i.toFloat(), Random.nextInt(50,130).toFloat(),""))
    }
    return listP
}
@Composable
fun getMax(list: List<co.yml.charts.common.model.Point>):Float {
    var max = 0F
    list.forEach { point ->
        if (max < point.y) {
            max = point.y
        }
    }
    return max
}
@Composable
fun getMin(list: List<co.yml.charts.common.model.Point>):Float {
    var min = 140F
    list.forEach { point ->
        if (min > point.y) {
            min = point.y
        }
    }
    return min
}
@Composable
fun SingleLineGraph(){
    var steps=10
    var pointsList: List<co.yml.charts.common.model.Point> =getPoint()
    var max=getMax(pointsList)
    var min=getMin(pointsList)
    val xAxisData = AxisData.Builder()
        //расстояние
        .axisStepSize(100.dp)
        //задний фон
        .backgroundColor(Color.Blue)
        //счетчик, выполняемые шаги
        .steps(pointsList.size - 1)
        //текст
        .labelData { i -> i.toString()+"д" }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        //шаги
        .steps(steps)
        //задний фон
        .backgroundColor(Color.Red)
        //расстояние
        .labelAndAxisLinePadding(20.dp)
        //текст
        .labelData { i ->
            var yScale=(max-min)/steps.toFloat()
            ((i*yScale)+min).toInt().toString()
        }.build()


    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsList,
                    LineStyle(color = Color.DarkGray, width = 1.0f,),
                    IntersectionPoint(color=Color.Gray, radius = 2.dp),
                    SelectionHighlightPoint(color = Color.Cyan, radius = 5.dp),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(modifier = Modifier.fillMaxWidth().height(300.dp), lineChartData = lineChartData)
}