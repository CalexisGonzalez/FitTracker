package com.example.fittracker.mvp.presenter;

import com.example.fittracker.mvp.contract.UserSettingsContract;

public class UserSettingsPresenter implements UserSettingsContract.Presenter {
    private UserSettingsContract.View view;
    private UserSettingsContract.Model model;
    public UserSettingsPresenter(UserSettingsContract.View view, UserSettingsContract.Model model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void onEmailFocusChange() {

    }

    @Override
    public void onPasswordFocusChange() {

    }

    @Override
    public void onNameFocusChange() {

    }

    @Override
    public void onSurnameFocusChange() {

    }

    @Override
    public void onApplyChangesClick() {

    }

    @Override
    public void onCancelClick() {

    }

    @Override
    public void onCreate() {
        //TODO get user data from db,print it in the view
    }
}
