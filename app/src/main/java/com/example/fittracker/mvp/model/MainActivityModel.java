package com.example.fittracker.mvp.model;

import com.example.fittracker.DbQuery.DbQueryUserValid;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.LogInContract;

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
