package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

public class DbInsertUser extends AsyncTask<User, Void, Void> implements DbGenericQuery<Void, User> {
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
        mUserDao.insert(users[ConstantUtils.ZERO]);
        return null;
    }
}
