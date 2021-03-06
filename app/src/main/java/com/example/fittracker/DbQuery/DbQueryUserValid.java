package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbQueryUserValid extends AsyncTask<User, Void, Boolean> implements DbGenericQuery<Boolean, User> {
    private UserDao userDao;

    public DbQueryUserValid(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Boolean doInBackground(User... users) {
        return (userDao.fetchUserLogInValid(users[ConstantUtils.ZERO].getMail(), users[ConstantUtils.ZERO].getPassword()) == ConstantUtils.ONE);
    }

    @Override
    public Boolean executeQuery(User user) {
        try {
            return this.execute(user).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
