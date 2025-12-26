package com.example.budgetmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetmanager.data.BudgetDatabase
import com.example.budgetmanager.data.Expense
import com.example.budgetmanager.data.Income
import kotlinx.coroutines.launch

class BudgetViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = BudgetDatabase.getDatabase(application).budgetDao()

    val expenses = MutableLiveData<List<Expense>>()
    val income = MutableLiveData<List<Income>>()

    fun loadData() {
        viewModelScope.launch {
            expenses.postValue(dao.getAllExpenses())
            income.postValue(dao.getAllIncome())
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            dao.insertExpense(expense)
            expenses.postValue(dao.getAllExpenses())
        }
    }

    fun addIncome(incomeItem: Income) {
        viewModelScope.launch {
            dao.insertIncome(incomeItem)
            income.postValue(dao.getAllIncome())
        }
    }
    fun getMonth(timestamp: String): String {
        val time = timestamp.toLong()
        val cal = java.util.Calendar.getInstance()
        cal.timeInMillis = time

        val month = cal.get(java.util.Calendar.MONTH) + 1
        val year = cal.get(java.util.Calendar.YEAR)

        return "$month-$year"
    }
}

