package com.saulh.dailyexpensetracker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public int expenseID;

    @ColumnInfo
    public int creatorUsername;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public double amount;

}
