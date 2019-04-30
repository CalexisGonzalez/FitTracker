package com.example.fittracker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table " +
            "WHERE id == :userId")
    public User fetchUserData(int userId);

    @Query("SELECT COUNT(*) from user_table WHERE mail == :userEmail")
    public int fetchUserExist(String userEmail);

    @Query("SELECT COUNT(*) from user_table WHERE mail == :userEmail AND password == :userPassword")
    public int fetchUserLogInValid(String userEmail, String userPassword);

    @Query("DELETE FROM user_table")
    public void deleteTable();

    @Query("UPDATE user_table SET mail= :userEmail, password = :userPassword, name = :userName, surname = :userSurname " +
            "WHERE id == :id")
    public void updateTable(int id, String userEmail, String userPassword, String userName, String userSurname);

    @Query("SELECT id FROM user_table WHERE mail == :userEmail")
    public Integer fetchUserId(String userEmail);

    @Query("SELECT imageUrl FROM user_table WHERE id == :userId")
    public String fetchImageUrl(int userId);

    @Query("UPDATE user_table SET imageUrl = :imageUrl WHERE id == :userId")
    public void updateImageUrl(int userId, String imageUrl);
}
