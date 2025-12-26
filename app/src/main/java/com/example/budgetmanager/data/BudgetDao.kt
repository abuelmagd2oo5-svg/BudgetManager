package com.example.budgetmanager.data

import androidx.room.*

@Dao
interface BudgetDao {

    @Insert
    suspend fun insertExpense(expense: Expense)

    @Insert
    suspend fun insertIncome(income: Income)

    @Query("SELECT * FROM Expense")
    suspend fun getAllExpenses(): List<Expense>

    @Query("SELECT * FROM Income")
    suspend fun getAllIncome(): List<Income>
}
