package com.example.fittracker.DbQueries;

import android.os.AsyncTask;

import com.example.fittracker.User;
import com.example.fittracker.UserDao;

public class DbInsertUser extends AsyncTask<User, Void, Void> implements DbBooleanQuery<Void> {
    @Override
    public Void executeQuery(User user) {
        this.execute(user);
        return null;

    }

    private UserDao mUserDao;

    public DbInsertUser(UserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    @Override
    protected Void doInBackground(User... users) {
        mUserDao.insert(users[0]);
        return null;
    }
}
