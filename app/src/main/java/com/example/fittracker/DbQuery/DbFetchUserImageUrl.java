package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.UserDao;

import java.util.concurrent.ExecutionException;

public class DbFetchUserImageUrl extends AsyncTask<Integer, Void, String> implements DbGenericQuery<String, Integer> {
    private UserDao userDao;

    public DbFetchUserImageUrl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String doInBackground(Integer... ids) {
        return (userDao.fetchImageUrl(ids[ConstantUtils.ZERO]));
    }

    @Override
    public String executeQuery(Integer id) {
        try {
            return this.execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
