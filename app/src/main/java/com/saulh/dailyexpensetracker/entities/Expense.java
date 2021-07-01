package com.saulh.dailyexpensetracker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    int expenseID;

    @ColumnInfo
    String description;

    @ColumnInfo
    double amount;

    @ColumnInfo
    Date date;
}
