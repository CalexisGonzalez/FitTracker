package com.example.fittracker.mvp.models;

import android.os.AsyncTask;

import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contracts.SignUpContract;

import java.util.concurrent.ExecutionException;

public class SignUpModel implements SignUpContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;

    public SignUpModel(UserRoomDatabase db) {
        this.db = db;
        userDao = db.userDao();
    }

    @Override
    public void registrateUser(User user) {
        new insertAsyncTask(userDao).execute(user);
    }

    @Override
    public boolean userDoesExist(User user) {
        try {
            return (new fetchAsyncTask(userDao).execute(user).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        insertAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

    private static class fetchAsyncTask extends AsyncTask<User, Void, Boolean> {
        private UserDao mUserDao;
        private final int ONE = 1;

        fetchAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Boolean doInBackground(User... users) {
            return mUserDao.fetchUserExist(users[0].getMail()) == ONE;
        }
    }
}
