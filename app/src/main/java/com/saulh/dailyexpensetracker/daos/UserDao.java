package com.saulh.dailyexpensetracker.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.saulh.dailyexpensetracker.entities.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);
}
