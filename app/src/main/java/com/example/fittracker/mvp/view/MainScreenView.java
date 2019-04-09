package com.example.fittracker.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.activity.UserSettingsActivity;
import com.example.fittracker.mvp.contract.MainScreenContract;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

public class MainScreenView implements MainScreenContract.View {
    private WeakReference<Activity> activity;

    public MainScreenView(Activity activity) {
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
    }

    @Override
    public void onSettingsPressed() {
        Intent intent = new Intent(activity.get(), UserSettingsActivity.class);
        activity.get().startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.get().startActivity(startMain);
    }

    @Override
    public void onLogOutPressed() {
        SharedPreferences.Editor editor = activity.get().
                getSharedPreferences(ConstantUtils.USER_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putInt(ConstantUtils.USER_PREFERENCES_ID, ConstantUtils.NEGATIVE_ONE);
        editor.apply();
        activity.get().finish();
    }
}
