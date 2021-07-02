package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.saulh.dailyexpensetracker.daos.ExpenseDao;
import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.Expense;

public class AddExpense extends AppCompatActivity {
    EditText mExpenseName, mExpenseAmount, mExpenseDate;
    ImageButton mAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mAddExpense = findViewById(R.id.imgButtonSave);
        mExpenseName = findViewById(R.id.editTextExpenseName);
        mExpenseAmount = findViewById(R.id.editTextAmount);
        mExpenseDate = findViewById(R.id.editTextDate);

        mAddExpense.setOnClickListener(v -> {
            AppDatabase db = AppDatabase.getDBInstance(getApplicationContext());
            ExpenseDao expenseDao = db.expenseDao();
            Expense expense = new Expense();
            expense.description = mExpenseAmount.getText().toString();
            expense.amount = Double.parseDouble(mExpenseAmount.getText().toString());
            expense.date = mExpenseDate.getText().toString();
            expense.creatorUsername = getIntent().getStringExtra(LoginActivity.LOGGED_IN_USERNAME);
            expenseDao.insertExpense(expense);
        });
    }
}