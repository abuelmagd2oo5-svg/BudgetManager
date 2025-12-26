package com.example.budgetmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgetmanager.data.Expense
import com.example.budgetmanager.viewmodel.BudgetViewModel

@Composable
fun AddExpenseScreen(
    vm: BudgetViewModel,
    onDone: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Food") }

    val categories = listOf("Food", "Transport", "Bills", "Shopping", "Entertainment")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Add Expense", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )

        Spacer(Modifier.height(10.dp))

        Text("Category")

        DropdownMenuBox(
            categories = categories,
            selected = category,
            onSelected = { category = it }
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (title.isNotEmpty() && amount.isNotEmpty()) {
                    vm.addExpense(
                        Expense(
                            title = title,
                            amount = amount.toDouble(),
                            category = category,
                            date = System.currentTimeMillis().toString()
                        )
                    )
                    onDone()
                }
            }
        ) {
            Text("Save Expense")
        }
    }
}
@Composable
fun DropdownMenuBox(
    categories: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selected)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelected(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
