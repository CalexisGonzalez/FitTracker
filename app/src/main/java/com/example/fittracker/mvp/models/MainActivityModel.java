package com.example.fittracker.mvp.models;

import android.os.AsyncTask;

import com.example.fittracker.User;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contracts.LogInContract;

import java.util.concurrent.ExecutionException;

public class MainActivityModel implements LogInContract.Model {
    private UserRoomDatabase db;
    private UserDao userDao;

    public MainActivityModel(UserRoomDatabase db) {

        this.db = db;
        userDao = db.userDao();
    }

    @Override
    public boolean isValidLogIn(User userInput) {
        try {
            return new fetchAsyncTask(userDao).execute(userInput).get();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class fetchAsyncTask extends AsyncTask<User, Void, Boolean> {
        private UserDao userDao;
        private final int ONE = 1;

        fetchAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(User... users) {
            return (userDao.fetchUserLogInValid(users[0].getMail(), users[0].getPassword()) == ONE);
        }
    }
}
