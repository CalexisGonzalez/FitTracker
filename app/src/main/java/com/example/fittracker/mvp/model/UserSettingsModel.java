package com.example.fittracker.mvp.model;

import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.UserSettingsContract;

public class UserSettingsModel implements UserSettingsContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;
    public UserSettingsModel(UserRoomDatabase db){
        this.db = db;
        this.userDao = db.userDao();
    }

    @Override
    public void onApplyChanges() {

    }

    @Override
    public void getUserData(User user) {

    }
}
