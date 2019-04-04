package com.example.fittracker.mvp.contract;

import com.example.fittracker.User;

public interface UserSettingsContract {
    interface Model{
        public void onApplyChanges(User user);
        public User getUserData(int id);
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
        public void onCreate(String mail,String password,String name,String surname);

        void printError();
    }
    interface Presenter{
        public void onEmailClick();
        public void onPasswordClick();
        public void onNameClick();
        public void onSurnameClick();
        public void onApplyChangesClick();
        public void onCancelClick();
        public void onCreate();
    }
}
