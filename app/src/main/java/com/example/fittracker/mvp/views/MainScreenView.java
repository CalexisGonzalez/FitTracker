package com.example.fittracker.mvp.views;

import android.app.Activity;
import android.content.Intent;

import com.example.fittracker.activities.UserSettingsActivity;
import com.example.fittracker.mvp.contracts.MainScreenContract;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

public class MainScreenView implements MainScreenContract.View {
    private WeakReference<Activity> activity;

    public MainScreenView(Activity activity){
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this,activity);
    }

    @Override
    public void onSettingsPressed() {
        Intent intent = new Intent(activity.get(), UserSettingsActivity.class);
        activity.get().startActivity(intent);
    }
}
