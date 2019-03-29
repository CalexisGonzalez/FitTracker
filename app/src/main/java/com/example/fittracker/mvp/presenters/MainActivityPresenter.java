package com.example.fittracker.mvp.presenters;

import com.example.fittracker.User;
import com.example.fittracker.mvp.contracts.LogInContract;

public class MainActivityPresenter implements LogInContract.Presenter {
    private LogInContract.View mView;
    private LogInContract.Model mModel;
    private final String EMPTY = "";

    public MainActivityPresenter(LogInContract.View view, LogInContract.Model model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onLogInPressed() {
        if (mModel.isValidLogIn(new User(mView.getEmail(), mView.getPassword(), EMPTY, EMPTY))) {
            mView.onValidLogin();
        } else {
            mView.logInError();
        }
    }

    @Override
    public void onSignUpPressed() {
        mView.onSignUpPressed();
    }
}
