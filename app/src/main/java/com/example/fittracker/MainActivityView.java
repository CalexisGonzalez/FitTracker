package com.example.fittracker;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityView implements LogInContract.View {
    @BindView(R.id.mail_usuario) EditText mail;
    @BindView(R.id.password) EditText password;
    private WeakReference<Activity> actividad;

    public MainActivityView(Activity actividad) {
        this.actividad = new WeakReference<Activity>(actividad);
        ButterKnife.bind(this, this.actividad.get());
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
        Toast.makeText(actividad.get(), R.string.logInError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpPressed() {
        //Change to registration screen
        Toast.makeText(actividad.get(), R.string.onSignUpPressed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidLogin() {
        //Change to valid login screen
        Toast.makeText(actividad.get(), R.string.onValidLogin, Toast.LENGTH_SHORT).show();
    }
}
