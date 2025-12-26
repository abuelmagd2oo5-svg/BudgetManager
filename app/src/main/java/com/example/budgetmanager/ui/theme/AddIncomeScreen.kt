package com.example.budgetmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgetmanager.data.Income
import com.example.budgetmanager.viewmodel.BudgetViewModel

@Composable
fun AddIncomeScreen(vm: BudgetViewModel, onDone: () -> Unit) {

    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Add Income", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (amount.isNotEmpty()) {
                    vm.addIncome(
                        Income(
                            amount = amount.toDouble(),
                            date = System.currentTimeMillis().toString()
                        )
                    )
                    onDone()
                }
            }
        ) {
            Text("Save")
        }
    }
}
