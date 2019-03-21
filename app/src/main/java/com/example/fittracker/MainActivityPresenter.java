package com.example.fittracker;

public class MainActivityPresenter implements LogInContract.Presenter{
    private LogInContract.View mView;
    private LogInContract.Model mModel;
    public MainActivityPresenter(LogInContract.View view, LogInContract.Model model){
        mView=view;
        mModel=model;
    }

    @Override
    public void onLogInPressed() {
        if (mModel.isValidLogIn(mView.getEmail(),mView.getPassword())){
            onValidLogin();
        }else{
            onInvalidLogin();
        }
    }

    @Override
    public void onValidLogin() {
        mView.onValidLogin();
    }

    @Override
    public void onInvalidLogin() {
        mView.logInError();
    }

    @Override
    public void onSignUpPressed() {
        mView.onSignUpPressed();
    }
}
