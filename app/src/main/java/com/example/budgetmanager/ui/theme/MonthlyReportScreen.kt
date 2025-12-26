package com.example.budgetmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgetmanager.viewmodel.BudgetViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.budgetmanager.ui.components.PieChartView

@Composable
fun MonthlyReportScreen(vm: BudgetViewModel, onBack: () -> Unit) {

    val expenses by vm.expenses.observeAsState(emptyList())

    val grouped = expenses.groupBy { vm.getMonth(it.date) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Monthly Report", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(20.dp))

        grouped.forEach { (month, list) ->
            val total = list.sumOf { it.amount }

            Text("Month: $month", style = MaterialTheme.typography.titleMedium)
            Text("Total: $total EGP")

            PieChartView(
                list.groupBy { it.category }
                    .mapValues { it.value.sumOf { e -> e.amount } }
            )

            Spacer(Modifier.height(30.dp))
        }

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}
