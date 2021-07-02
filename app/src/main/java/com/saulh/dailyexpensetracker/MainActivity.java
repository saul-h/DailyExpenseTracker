package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saulh.dailyexpensetracker.daos.ExpenseDao;
import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.Expense;
import com.saulh.dailyexpensetracker.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFAB;
    Context context;
    RecyclerView mRecyclerView;
    String username;
    List<Expense> userExpenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        AppDatabase db = AppDatabase.getDBInstance(context);
        ExpenseDao expenseDao = db.expenseDao();
        UserDao userDao = db.userDao();

        username = getIntent().getStringExtra(LoginActivity.LOGGED_IN_USERNAME);

        userExpenseList = expenseDao.getExpensesForUser(username);

        mFAB = findViewById(R.id.floatingActionButton);


        mFAB.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddExpense.class);
            intent.putExtra(LoginActivity.LOGGED_IN_USERNAME, username);
            startActivity(intent);
        });
    }
}