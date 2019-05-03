package com.example.fittracker.DbQuery;

import android.os.AsyncTask;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.UserDao;

public class DbUpdateUserImageUrl extends AsyncTask<DbUpdateImagePojo, Void, Void>
        implements DbGenericQuery<Void, DbUpdateImagePojo> {
    private UserDao userDao;

    public DbUpdateUserImageUrl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(DbUpdateImagePojo... images) {
        DbUpdateImagePojo image = images[ConstantUtils.ZERO];
        userDao.updateImageUrl(image.getUserId(), image.getImageUrl());
        return null;
    }

    @Override
    public Void executeQuery(DbUpdateImagePojo user) {
        this.execute(user);
        return null;
    }
}
