package com.saulh.dailyexpensetracker.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.saulh.dailyexpensetracker.entities.Expense;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insertExpense(Expense expense);

    @Query("SELECT * FROM expense WHERE expense.creatorUsername LIKE :username")
    List<Expense> getExpensesForUser(String username);
}
