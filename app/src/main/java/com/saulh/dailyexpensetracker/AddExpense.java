package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.saulh.dailyexpensetracker.daos.ExpenseDao;
import com.saulh.dailyexpensetracker.entities.Expense;

public class AddExpense extends AppCompatActivity {
    EditText mExpenseName, mExpenseAmount, mExpenseDate;
    ImageButton mAddExpense;
    AppDatabase db;
    ExpenseDao expenseDao;
    Expense expense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mAddExpense = findViewById(R.id.imgButtonSave);
        mExpenseName = findViewById(R.id.editTextExpenseName);
        mExpenseAmount = findViewById(R.id.editTextAmount);
        mExpenseDate = findViewById(R.id.editTextDate);

        db = AppDatabase.getDBInstance(getApplicationContext());
        expenseDao = db.expenseDao();


        mAddExpense.setOnClickListener(v -> {
            expense = new Expense();
            expense.description = (String)mExpenseName.getText().toString();
            expense.amount = Double.parseDouble(mExpenseAmount.getText().toString());
            expense.date = (String)mExpenseDate.getText().toString();
            expense.creatorUsername = (String) getIntent().getStringExtra(LoginActivity.LOGGED_IN_USERNAME);
            expenseDao.insertExpense(expense);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}