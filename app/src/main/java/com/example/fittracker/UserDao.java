package com.example.fittracker;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table " +
            "WHERE mail == :userEmail AND password == :userPassword")
    public LiveData<User> fetchUserData(String userEmail, String userPassword);

    @Query("SELECT COUNT(*) from user_table WHERE mail == :userEmail")
    public int fetchUserExist(String userEmail);

    @Query("SELECT COUNT(*) from user_table WHERE mail == :userEmail AND password == :userPassword")
    public int fetchUserLogInValid(String userEmail, String userPassword);
}
