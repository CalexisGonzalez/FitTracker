package com.example.fittracker.mvp.presenter;

import android.content.Intent;
import android.net.Uri;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.mvp.contract.SignUpContract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.Model model;
    private SignUpContract.View view;

    public SignUpPresenter(SignUpContract.Model model, SignUpContract.View view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onSignUpPressed() {
        if (fieldMissing()) {
            view.missingFieldError();
        } else if (!isEmailValid(view.getEmail())) {
            view.emailFormatError();
        } else if (!passwordsMatch()) {
            view.passwordRepMatchError();
        } else {
            User user = new User(view.getEmail(), view.getPassword(),
                    view.getName(), view.getSurname());
            if (model.userDoesExist(user)) {
                view.userAlreadyExists();
            } else {
                model.registrateUser(user);
                view.succesfulSignUp();
            }
        }
    }

    @Override
    public void onCancelPressed() {
        view.onCancel();
    }

    private boolean fieldMissing() {
        return ((view.getEmail().equals(ConstantUtils.EMPTY)) || (view.getPassword().equals(ConstantUtils.EMPTY)) ||
                (view.getRepPassword().equals(ConstantUtils.EMPTY)) || (view.getName().equals(ConstantUtils.EMPTY)) ||
                (view.getSurname().equals(ConstantUtils.EMPTY)));
    }

    private boolean passwordsMatch() {
        return (view.getPassword().equals(view.getRepPassword()));
    }

    private boolean isEmailValid(String email) {
        String expression = ConstantUtils.EMAIL_FORMAT;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onSendMailPressed() {
        if (fieldMissing()){
            view.missingFieldError();
        }else if (!isEmailValid(view.getEmail())){
            view.emailFormatError();
        }else {
            //Mail intent creation here
        }
    }
}
