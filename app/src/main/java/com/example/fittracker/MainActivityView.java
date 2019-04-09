package com.example.fittracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fittracker.activity.MainScreenActivity;
import com.example.fittracker.activity.SignUpActivity;
import com.example.fittracker.mvp.contract.LogInContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityView implements LogInContract.View {
    @BindView(R.id.main_activity_text_mail_user) EditText mail;
    @BindView(R.id.main_activity_text_password) EditText password;
    private WeakReference<Activity> activity;

    public MainActivityView(Activity actividad) {
        ButterKnife.bind(this, actividad);
        this.activity = new WeakReference<Activity>(actividad);
    }

    @Override
    public String getEmail() {
        return mail.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void logInError() {
        Toast.makeText(activity.get(), R.string.error_loginerror, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpPressed() {
        //Change to registration screen
        Intent intent = new Intent(activity.get(), SignUpActivity.class);
        activity.get().startActivity(intent);
    }

    @Override
    public void onValidLogin(int id) {
        //Change to valid login screen
        SharedPreferences.Editor editor = activity.get().
                getSharedPreferences(ConstantUtils.USER_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putInt(ConstantUtils.USER_PREFERENCES_ID, id);
        editor.apply();
        Intent intent = new Intent(activity.get(), MainScreenActivity.class);
        activity.get().startActivity(intent);
    }

}
