package com.example.fittracker.mvp.model;

import android.content.SharedPreferences;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.DbQuery.DbFetchUserId;
import com.example.fittracker.DbQuery.DbQueryUserValid;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.LogInContract;

public class MainActivityModel implements LogInContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;
    private SharedPreferences preferences;

    public MainActivityModel(UserRoomDatabase db, SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
        this.db = db;
        userDao = db.userDao();
    }

    @Override
    public boolean isValidLogIn(User userInput) {
        return new DbQueryUserValid(userDao).executeQuery(userInput);
    }

    @Override
    public int getUserId(User user) {
        return new DbFetchUserId(userDao).executeQuery(user);
    }

    @Override
    public int getSharedPreferencesInt() {
        return preferences.getInt(ConstantUtils.USER_PREFERENCES_ID, ConstantUtils.ZERO);
    }

    @Override
    public boolean existSharedPreferences() {
        return preferences.contains(ConstantUtils.USER_PREFERENCES_ID);
    }
}
