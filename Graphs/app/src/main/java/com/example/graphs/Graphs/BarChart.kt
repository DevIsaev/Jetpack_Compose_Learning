package com.example.graphs.Graphs

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import kotlin.random.Random

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