package com.example.fittracker.mvp.models;

import com.example.fittracker.DbQueries.DbInsertUser;
import com.example.fittracker.DbQueries.DbQueryUserExist;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contracts.SignUpContract;

public class SignUpModel implements SignUpContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;

    public SignUpModel(UserRoomDatabase db) {
        this.db = db;
        userDao = db.userDao();
    }

    @Override
    public void registrateUser(User user) {
        new DbInsertUser(userDao).execute(user);
    }

    @Override
    public boolean userDoesExist(User user) {
        return new DbQueryUserExist(userDao).executeQuery(user);
    }
}
