package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbFetchUserId extends AsyncTask<User, Void, Integer> implements DbGenericQuery<Integer, User> {
    private UserDao userDao;

    public DbFetchUserId(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Integer doInBackground(User... users) {
        return userDao.fetchUserId(users[ConstantUtils.ZERO].getMail());
    }

    @Override
    public Integer executeQuery(User user) {
        try {
            return this.execute(user).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
