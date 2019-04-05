package com.example.fittracker.mvp.presenter;

import android.content.Intent;
import android.net.Uri;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.User;
import com.example.fittracker.mvp.contract.UserSettingsContract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSettingsPresenter implements UserSettingsContract.Presenter {
    private UserSettingsContract.View view;
    private UserSettingsContract.Model model;
    private int userId;

    public UserSettingsPresenter(UserSettingsContract.View view, UserSettingsContract.Model model) {
        this.view = view;
        this.model = model;
        userId = model.getLoggedUserId();
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
        if (fieldMissing()) {
            view.printMissingFieldError();
        } else {
            User user = new User(view.getMail(), view.getPassword(), view.getName(), view.getSurname());
            user.setId(userId);
            model.onApplyChanges(user);
            view.onApplyChangesClick();
        }
    }

    @Override
    public void onCancelClick() {
        view.onCancelClick();
    }

    @Override
    public void init() {
        // get user data from db,print it in the view
        User user = model.getUserData(userId);
        if (user != null) {
            view.initMail(user.getMail());
            view.initPassword(user.getPassword());
            view.initName(user.getName());
            view.initSurname(user.getSurname());
        } else {
            view.printFetchingError();
        }
    }

    @Override
    public void onSendMailClick() {
        if (fieldMissing()) {
            view.printMissingFieldError();
        }else if (!isEmailValid(view.getMail())){
            view.printInvalidEmail();
        }else {
           //Mail intent creation here
        }
    }

    private boolean fieldMissing() {
        return ((view.getMail().equals(ConstantUtils.EMPTY)) || (view.getPassword().equals(ConstantUtils.EMPTY)) ||
                (view.getName().equals(ConstantUtils.EMPTY)) ||
                (view.getSurname().equals(ConstantUtils.EMPTY)));
    }

    private boolean isEmailValid(String email) {
        String expression = ConstantUtils.EMAIL_FORMAT;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
