package com.example.fittracker.DbQueries;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbQueryUserExist extends AsyncTask<User, Void, Boolean> implements DbBooleanQuery {
    private UserDao mUserDao;

    public DbQueryUserExist(UserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    @Override
    protected Boolean doInBackground(User... users) {
        return mUserDao.fetchUserExist(users[0].getMail()) == ConstantUtils.ONE;
    }

    @Override
    public boolean executeQuery(User user) {
        try {
            return this.execute(user).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
