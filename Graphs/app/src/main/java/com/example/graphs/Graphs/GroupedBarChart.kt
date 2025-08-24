package com.example.graphs.Graphs

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.GroupBar
import co.yml.charts.ui.barchart.models.GroupBarChartData
import kotlin.random.Random

object DataUtiles {
    fun getGroupBarChartData(
        groupCount: Int,
        maxRange: Int,
        barsPerGroup: Int
    ): List<GroupBar> {
        return List(groupCount) { groupIndex ->
            val groupLabel = "Group ${groupIndex + 1}"

            val bars = List(barsPerGroup) { barIndex ->
                val value = Random.nextFloat() * maxRange
                BarData(
                    point = co.yml.charts.common.model.Point(barIndex.toFloat(), value),
                    color = getColorForIndex(barIndex),
                    label = "Bar ${barIndex + 1}",
                    gradientColorList = listOf(
                        getColorForIndex(barIndex),
                        getLighterColor(getColorForIndex(barIndex))
                    ),
                    dataCategoryOptions = DataCategoryOptions(),
                    description = "Value of $groupLabel, Bar ${barIndex + 1} is ${String.format("%.2f", value)}"
                )
            }

            // Используем класс GroupBar из библиотеки вместо нашего GroupBarData
            GroupBar(groupLabel, bars)
        }
    }

    fun getColorForIndex(index: Int): Color {
        return when (index % 5) {
            0 -> Color(0xFF4CAF50) // Green
            1 -> Color(0xFF2196F3) // Blue
            2 -> Color(0xFFFFC107) // Amber
            3 -> Color(0xFFF44336) // Red
            else -> Color(0xFF9C27B0) // Purple
        }
    }

    fun getLighterColor(color: Color): Color {
        return Color(
            red = (color.red * 0.8f).coerceIn(0f, 1f),
            green = (color.green * 0.8f).coerceIn(0f, 1f),
            blue = (color.blue * 0.8f).coerceIn(0f, 1f),
            alpha = color.alpha
        )
    }
}

// Function to get color palette
fun getColorPaletteList(barSize: Int): List<Color> {
    return List(barSize) { index ->
        DataUtiles.getColorForIndex(index)
    }
}

@Composable
fun GroupedBarChart() {
    val barChartListSize = 4 // Количество групп
    val maxRange = 100 // Максимальное значение
    val yStepSize = 5 // Количество шагов на оси Y
    val eachGroupBarSize = 3 // Количество столбцов в каждой группе

    val groupBarData = DataUtiles.getGroupBarChartData(
        barChartListSize,
        maxRange,
        eachGroupBarSize
    )

    val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barColorPaletteList = getColorPaletteList(eachGroupBarSize)
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(groupBarData.size - 1)
        .bottomPadding(40.dp)
        .labelData { index -> groupBarData[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

    val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )

    GroupBarChart(
        modifier = Modifier.height(300.dp),
        groupBarChartData = groupBarChartData
    )
}