package com.example.graphs

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
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
import com.example.graphs.ui.theme.GraphsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphsTheme {
                Column(Modifier.verticalScroll(rememberScrollState()).padding(top=25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SingleLineGraph", textAlign = TextAlign.Center, fontSize=25.sp)
                    SingleLineGraph()
                    Text("BarChartGraph", textAlign = TextAlign.Center, fontSize=25.sp)
                    BarChartGraph()
                }
            }
        }
    }
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


    object DataUtils {
        fun getBarChartData(size: Int, maxRange: Int): List<BarData> {
            return List(size) { index ->
                val value = Random.nextFloat() * maxRange
                BarData(
                    point = co.yml.charts.common.model.Point(index.toFloat(), value),
                    color = Color(0xFFE91E63),
                    label = "${index + 1}д",
                    dataCategoryOptions = DataCategoryOptions(),
                    description = "Value of bar Item ${index + 1} is value ${String.format("%.2f", value)}"
                )
            }
        }
    }
    @Composable
    fun BarChartGraph() {
        val barChartListSize = 31// Количество столбцов
        val maxRange = 100 // Максимальное значение
        val yStepSize = 10 // Количество шагов на оси Y

        val barDataList = DataUtils.getBarChartData(barChartListSize, maxRange)

        val xAxisData = AxisData.Builder()
            .axisStepSize(30.dp)
            .steps(barDataList.size - 1)
            .bottomPadding(40.dp)
            .backgroundColor(Color.LightGray)
            .axisLabelAngle(20f)
            .labelData { index -> barDataList[index].label }
            .build()

        val yAxisData = AxisData.Builder()
            .steps(yStepSize)
            .labelAndAxisLinePadding(20.dp)
            .backgroundColor(Color.Yellow)
            .axisOffset(20.dp)
            .labelData { index -> (index * (maxRange / yStepSize)).toString() }
            .build()

        val barChartData = BarChartData(
            chartData = barDataList,
            xAxisData = xAxisData,
            yAxisData = yAxisData,
        )

        BarChart(
            modifier = Modifier.height(350.dp),
            barChartData = barChartData
        )
    }
}

