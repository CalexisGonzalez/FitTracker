package com.example.fittracker.mvp.contract;

import com.example.fittracker.User;

public interface SignUpContract {
    interface Presenter {
        void onSignUpPressed();

        void onCancelPressed();
    }

    interface Model {
        void registrateUser(User user);

        boolean userDoesExist(User user);
    }

    interface View {
        String getEmail();

        String getPassword();

        String getRepPassword();

        void passwordRepMatchError();

        void missingFieldError();

        String getName();

        String getSurname();

        void onCancel();

        void userAlreadyExists();

        void succesfulSignUp();
    }
}
