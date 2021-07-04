package com.saulh.dailyexpensetracker.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String password;

    @ColumnInfo
    public double annualIncome;

    @ColumnInfo
    public double desiredSavings;



}
