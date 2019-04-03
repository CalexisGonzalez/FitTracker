package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbQueryUserExist extends AsyncTask<User, Void, Boolean> implements DbGenericQuery<Boolean> {
    private UserDao mUserDao;

    public DbQueryUserExist(UserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    @Override
    protected Boolean doInBackground(User... users) {
        return mUserDao.fetchUserExist(users[ConstantUtils.ZERO].getMail()) == ConstantUtils.ONE;
    }

    @Override
    public Boolean executeQuery(User user) {
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
