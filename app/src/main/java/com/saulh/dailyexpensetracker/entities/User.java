package com.saulh.dailyexpensetracker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    Integer uid;

    @ColumnInfo
    String email;

    @ColumnInfo
    String name;

    @ColumnInfo
    String password;
}
