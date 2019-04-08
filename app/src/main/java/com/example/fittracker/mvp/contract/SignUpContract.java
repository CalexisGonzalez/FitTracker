package com.example.fittracker.mvp.contract;

import android.content.Intent;

import com.example.fittracker.GMailSender;
import com.example.fittracker.User;

public interface SignUpContract {
    interface Presenter {
        void onSignUpPressed();

        void onCancelPressed();

        void onSendMailPressed();
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

        void emailFormatError();

        void onSendMailPressed(GMailSender sender, String subject, String body, String recipient);

        boolean checkboxMailPressed();
    }
}
