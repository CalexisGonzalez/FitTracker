package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.UserDao;

public class DbUpdateUserData extends AsyncTask<User,Void,Void> implements DbGenericQuery<Void,User> {
    private UserDao userDao;

    public DbUpdateUserData(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(User... users) {
        User user = users[ConstantUtils.ZERO];
        userDao.updateTable(user.getId(),user.getMail(),user.getPassword(),user.getName(),user.getSurname());
        return null;
    }

    @Override
    public Void executeQuery(User user) {
        this.execute(user);
        return null;
    }
}
