package com.example.fittracker;

public interface LogInContract {
    interface Presenter {
        void onSignUpPressed();

        void onLogInPressed();
    }

    interface Model {
        boolean isValidLogIn(User user);
    }

    interface View {
        void logInError();

        String getEmail();

        String getPassword();

        void onSignUpPressed();

        void onValidLogin();
    }
}
