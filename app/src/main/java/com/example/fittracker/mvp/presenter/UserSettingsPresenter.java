package com.example.fittracker.mvp.presenter;

import com.example.fittracker.User;
import com.example.fittracker.mvp.contract.UserSettingsContract;

public class UserSettingsPresenter implements UserSettingsContract.Presenter {
    private UserSettingsContract.View view;
    private UserSettingsContract.Model model;
    private int id;

    public UserSettingsPresenter(UserSettingsContract.View view, UserSettingsContract.Model model, int id) {
        this.view = view;
        this.model = model;
        this.id = id;
    }

    @Override
    public void onEmailClick() {
        view.onEmailClick();
    }

    @Override
    public void onPasswordClick() {
        view.onPasswordClick();
    }

    @Override
    public void onNameClick() {
        view.onNameClick();
    }

    @Override
    public void onSurnameClick() {
        view.onSurnameClick();
    }

    @Override
    public void onApplyChangesClick() {
        User user = new User(view.getMail(), view.getPassword(), view.getName(), view.getSurname());
        user.setId(id);
        model.onApplyChanges(user);
        view.onApplyChangesClick();
    }

    @Override
    public void onCancelClick() {
        view.onCancelClick();
    }

    @Override
    public void onCreate() {
        // TODO get user data from db,print it in the view
        User user = model.getUserData(id);
        if (user != null) {
            view.onCreate(user.getMail(), user.getPassword(), user.getName(), user.getSurname());
        } else {
            view.printError();
        }
    }
}
