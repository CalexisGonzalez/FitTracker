package com.example.fittracker;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityView implements LogInContract.View{
    private EditText mail;
    private EditText password;
    private Activity actividad;
    public MainActivityView(EditText mail, EditText password,Activity actividad){
        this.mail=mail;
        this.password=password;
        this.actividad=actividad;
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
        Toast.makeText(actividad,"Informacion de inicio incorrecta",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpPressed() {
        //Cambio a pantalla de registro
    }

    @Override
    public void onValidLogin() {
        //Cambio a pantalla principal de la app
    }
}
