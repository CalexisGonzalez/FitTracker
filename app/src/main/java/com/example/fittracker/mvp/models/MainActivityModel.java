package com.example.fittracker.mvp.models;

import com.example.fittracker.DbQueries.DbQueryUserValid;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contracts.LogInContract;

public class MainActivityModel implements LogInContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;

    public MainActivityModel(UserRoomDatabase db) {

        this.db = db;
        userDao = db.userDao();
    }

    @Override
    public boolean isValidLogIn(User userInput) {
        return new DbQueryUserValid(userDao).executeQuery(userInput);
    }
}
