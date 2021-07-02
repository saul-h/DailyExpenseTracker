package com.saulh.dailyexpensetracker.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.saulh.dailyexpensetracker.entities.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user WHERE username = :username")
    User getUserByUsername(String username);
}
