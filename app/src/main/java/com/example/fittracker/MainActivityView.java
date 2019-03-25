package com.example.fittracker;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;

public class MainActivityView implements LogInContract.View {
    @BindView(R.id.mail_usuario) EditText mail;
    @BindView(R.id.password) EditText password;
    private Activity actividad;

    public MainActivityView(Activity actividad) {
        this.actividad = actividad;
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
        Toast.makeText(actividad, R.string.logInError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpPressed() {
        //Cambio a pantalla de registro
        Toast.makeText(actividad, R.string.onSignUpPressed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidLogin() {
        //Cambio a pantalla principal de la app
        Toast.makeText(actividad, R.string.onValidLogin, Toast.LENGTH_SHORT).show();
    }
}
