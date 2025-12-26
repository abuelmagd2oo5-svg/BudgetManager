package com.example.budgetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetmanager.ui.*
import com.example.budgetmanager.viewmodel.BudgetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val vm: BudgetViewModel = viewModel()
            var screen by remember { mutableStateOf("home") }

            when (screen) {
                "home" -> HomeScreen(
                    vm,
                    onAddIncome = { screen = "income" },
                    onAddExpense = { screen = "expense" },
                    onMonthlyReport = { screen = "report" }
                )

                "report" -> MonthlyReportScreen(vm) {
                    screen = "home"
                }
                "income" -> AddIncomeScreen(vm) {
                    screen = "home"
                }

                "expense" -> AddExpenseScreen(vm) {
                    screen = "home"
                }

            }

        }
    }
}
