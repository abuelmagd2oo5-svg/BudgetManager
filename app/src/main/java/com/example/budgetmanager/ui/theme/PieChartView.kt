package com.example.budgetmanager.ui.components

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart// يظهر خطأ
import com.github.mikephil.charting.data.*// يظهر خطأ

@Composable
fun PieChartView(data: Map<String, Double>) {

    AndroidView(factory = { context ->// يظهر خطأ
        PieChart(context).apply {// يظهر خطأ
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                600
            )
        }
    }, update = { chart ->

        val entries = data.map {
            PieEntry(it.value.toFloat(), it.key)
        }

        val dataSet = PieDataSet(entries, "Expenses")
        dataSet.colors = listOf(
            Color.RED, Color.BLUE, Color.GREEN,
            Color.MAGENTA, Color.CYAN
        )

        val pieData = PieData(dataSet)
        chart.data = pieData
        chart.invalidate()
    })
}
