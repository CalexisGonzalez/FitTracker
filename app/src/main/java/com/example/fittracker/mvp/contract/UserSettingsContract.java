package com.example.fittracker.mvp.contract;

import android.content.Intent;

import com.example.fittracker.User;

public interface UserSettingsContract {
    interface Model{
        public void onApplyChanges(User user);
        public User getUserData(int id);
        public int getLoggedUserId();
    }
    interface View{
        public void onEmailClick();
        public void onPasswordClick();
        public void onNameClick();
        public void onSurnameClick();
        public void onApplyChangesClick();
        public void onCancelClick();
        public String getName();
        public String getSurname();
        public String getMail();
        public String getPassword();
        public void initMail(String mail);
        public void initPassword(String password);
        public void initName(String name);
        public void initSurname(String surname);
        public void onSendMailClick(Intent intent);
        public void printFetchingError();
        public void printMissingFieldError();
        public void printInvalidEmail();
    }
    interface Presenter{
        public void onEmailClick();
        public void onPasswordClick();
        public void onNameClick();
        public void onSurnameClick();
        public void onApplyChangesClick();
        public void onCancelClick();
        public void init();
        public void onSendMailClick();
    }
}
