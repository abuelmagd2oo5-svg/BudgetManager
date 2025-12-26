package com.example.budgetmanager.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.budgetmanager.viewmodel.BudgetViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.budgetmanager.ui.components.FinanceCard
import com.example.budgetmanager.ui.components.BackgroundImage
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween

@Composable
fun HomeScreen(
    vm: BudgetViewModel,
    onAddIncome: () -> Unit,
    onAddExpense: () -> Unit,
    onMonthlyReport: () -> Unit
) {
    val expenses by vm.expenses.observeAsState(emptyList())
    val income by vm.income.observeAsState(emptyList())

    val totalIncome = income.sumOf { it.amount }
    val totalExpense = expenses.sumOf { it.amount }
    val balance = totalIncome - totalExpense

    BackgroundImage {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                "Budget Manager",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(16.dp))

            // Balance Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.92f)
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Balance", style = MaterialTheme.typography.titleMedium)
                    val animatedBalance by animateFloatAsState(
                        targetValue = balance.toFloat(),
                        animationSpec = tween(700),
                        label = "balance"
                    )

                    Text(
                        "${animatedBalance.toInt()} EGP",
                        style = MaterialTheme.typography.headlineLarge
                    )

                }
            }

            Spacer(Modifier.height(20.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FinanceCard(
                    title = "Income",
                    amount = totalIncome,
                    icon = Icons.Filled.TrendingUp,
                    color = MaterialTheme.colorScheme.primary
                )

                FinanceCard(
                    title = "Expense",
                    amount = totalExpense,
                    icon = Icons.Filled.TrendingDown,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = onAddIncome,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.AttachMoney, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Add Income")
            }

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = onAddExpense,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(Icons.Filled.TrendingDown, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Add Expense")
            }

            Spacer(Modifier.height(10.dp))

            OutlinedButton(
                onClick = onMonthlyReport,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.PieChart, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Monthly Report")
            }
        }
    }
}
