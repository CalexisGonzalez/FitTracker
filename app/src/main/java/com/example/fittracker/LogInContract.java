package com.example.fittracker;

public interface LogInContract {
    interface Presenter{
        void onSignUpPressed();
        void onLogInPressed();
    }

    interface Model{
        boolean isValidLogIn(String mail, String password);
    }

    interface View{
        void logInError();
        String getEmail();
        String getPassword();
        void onSignUpPressed();
        void onValidLogin();
    }
}
