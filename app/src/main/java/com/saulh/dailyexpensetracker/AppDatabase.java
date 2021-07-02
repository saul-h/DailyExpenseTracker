package com.saulh.dailyexpensetracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.saulh.dailyexpensetracker.daos.ExpenseDao;
import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.Expense;
import com.saulh.dailyexpensetracker.entities.User;

@Database(entities = {User.class, Expense.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ExpenseDao expenseDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(), AppDatabase.class, "ExpenseTrackerDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
