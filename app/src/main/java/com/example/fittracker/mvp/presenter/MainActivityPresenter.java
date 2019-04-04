package com.example.fittracker.mvp.presenter;

import com.example.fittracker.User;
import com.example.fittracker.mvp.contract.LogInContract;

public class MainActivityPresenter implements LogInContract.Presenter {
    private LogInContract.View mView;
    private LogInContract.Model mModel;

    public MainActivityPresenter(LogInContract.View view, LogInContract.Model model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onLogInPressed() {
        User user = new User(mView.getEmail(), mView.getPassword());
        if (mModel.isValidLogIn(user)) {
            mView.onValidLogin(mModel.getUserId(user));
        } else {
            mView.logInError();
        }
    }

    @Override
    public void onSignUpPressed() {
        mView.onSignUpPressed();
    }
}
