package com.example.fittracker.mvp.model;

import android.content.SharedPreferences;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.DbQuery.DbUpdateImagePojo;
import com.example.fittracker.DbQuery.DbUpdateUserImageUrl;
import com.example.fittracker.UserDao;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.MarvelDialogContract;

public class MarvelDialogModel implements MarvelDialogContract.Model {
    private String imageUrl;
    private UserRoomDatabase db;
    private UserDao userDao;
    private SharedPreferences preferences;

    public MarvelDialogModel(String imageUrl, UserRoomDatabase db, SharedPreferences preferences) {
        this.imageUrl = imageUrl;
        this.db = db;
        this.userDao = db.userDao();
        this.preferences = preferences;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int getUserId() {
        return preferences.getInt(ConstantUtils.USER_PREFERENCES_ID, ConstantUtils.ZERO);
    }

    @Override
    public void updateImageUrl(int userId, String imageUrl) {
        new DbUpdateUserImageUrl(userDao).executeQuery(new DbUpdateImagePojo(userId, imageUrl));
    }
}
