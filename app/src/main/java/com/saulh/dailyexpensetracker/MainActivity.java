package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saulh.dailyexpensetracker.entities.User;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFAB;
    Context context;
    String username;
    // public LOGGED_IN_USER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        username = getIntent().getStringExtra(LoginActivity.LOGGED_IN_USERNAME);

        mFAB = findViewById(R.id.floatingActionButton);

        mFAB.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddExpense.class);
            intent.putExtra(LoginActivity.LOGGED_IN_USERNAME, username);
            startActivity(intent);
        });
    }
}