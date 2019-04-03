package com.example.fittracker.mvp.contract;

import com.example.fittracker.User;

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
