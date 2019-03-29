package com.example.fittracker.mvp.presenters;

import com.example.fittracker.User;
import com.example.fittracker.mvp.contracts.SignUpContract;

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.Model model;
    private SignUpContract.View view;
    private final String EMPTY = "";

    public SignUpPresenter(SignUpContract.Model model, SignUpContract.View view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onSignUpPressed() {
        if (fieldMissing()) {
            view.missingFieldError();
        } else if (!passwordsMatch()) {
            view.passwordRepMatchError();
        } else {
            User user = new User(view.getEmail(), view.getPassword(),
                    view.getName(), view.getSurname());
            if (model.userDoesExist(user)) {
                view.userAlreadyExists(user);
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
        return ((view.getEmail().equals(EMPTY)) || (view.getPassword().equals(EMPTY)) ||
                (view.getRepPassword().equals(EMPTY)) || (view.getName().equals(EMPTY)) ||
                (view.getSurname().equals(EMPTY)));
    }

    private boolean passwordsMatch() {
        return (view.getPassword().equals(view.getRepPassword()));
    }
}
