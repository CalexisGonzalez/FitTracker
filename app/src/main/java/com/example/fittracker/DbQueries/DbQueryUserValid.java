package com.example.fittracker.DbQueries;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbQueryUserValid extends AsyncTask<User, Void, Boolean> implements DbBooleanQuery {
    private UserDao userDao;

    public DbQueryUserValid(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Boolean doInBackground(User... users) {
        return (userDao.fetchUserLogInValid(users[0].getMail(), users[0].getPassword()) == ConstantUtils.ONE);
    }

    @Override
    public boolean executeQuery(User user) {
        try {
            return this.execute(user).get();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
