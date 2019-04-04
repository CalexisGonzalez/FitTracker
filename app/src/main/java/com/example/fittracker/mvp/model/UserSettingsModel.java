package com.example.fittracker.mvp.model;

import com.example.fittracker.DbQuery.DbFetchUserData;
import com.example.fittracker.DbQuery.DbUpdateUserData;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.UserSettingsContract;

public class UserSettingsModel implements UserSettingsContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;

    public UserSettingsModel(UserRoomDatabase db) {
        this.db = db;
        this.userDao = db.userDao();
    }

    @Override
    public void onApplyChanges(User user) {
        new DbUpdateUserData(userDao).executeQuery(user);
    }

    @Override
    public User getUserData(int id) {
        return new DbFetchUserData(userDao).executeQuery(id);
    }
}
