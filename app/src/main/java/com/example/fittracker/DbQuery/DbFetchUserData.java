package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbFetchUserData extends AsyncTask<Integer, Void, User> implements DbGenericQuery<User,Integer> {
    private UserDao userDao;

    public DbFetchUserData(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected User doInBackground(Integer... ids) {
        return (userDao.fetchUserData(ids[ConstantUtils.ZERO]));
    }

    @Override
    public User executeQuery(Integer id) {
        try {
            return this.execute(id).get();
        } catch (ExecutionException|InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
