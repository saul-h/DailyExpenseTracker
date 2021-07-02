package com.saulh.dailyexpensetracker.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public int expenseID;

    @NonNull
    @ColumnInfo
    public String creatorUsername;

    @NonNull
    @ColumnInfo
    public String description;

    @ColumnInfo
    public double amount;

    @ColumnInfo
    public String date;
}
