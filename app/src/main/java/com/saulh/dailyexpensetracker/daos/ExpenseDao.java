package com.saulh.dailyexpensetracker.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.saulh.dailyexpensetracker.entities.Expense;

@Dao
public interface ExpenseDao {
    @Insert
    void insertExpense(Expense expense);


}
