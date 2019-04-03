package com.example.fittracker.mvp.contract;

import com.example.fittracker.User;

public interface UserSettingsContract {
    interface Model{
        public void onApplyChanges();
        public void getUserData(User user);
    }
    interface View{
        public void onEmailFocusChange();
        public void onPasswordFocusChange();
        public void onNameFocusChange();
        public void onSurnameFocusChange();
        public void onApplyChangesClick();
        public void onCancelClick();
        public String getName();
        public String getSurname();
        public String getMail();
        public String getPassword();
        public void onCreate(String mail,String password,String name,String surname);
    }
    interface Presenter{
        public void onEmailFocusChange();
        public void onPasswordFocusChange();
        public void onNameFocusChange();
        public void onSurnameFocusChange();
        public void onApplyChangesClick();
        public void onCancelClick();
        public void onCreate();
    }
}
