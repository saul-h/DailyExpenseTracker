package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFAB;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        mFAB = findViewById(R.id.floatingActionButton);

        mFAB.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddExpense.class);
            startActivity(intent);
        });
    }
}