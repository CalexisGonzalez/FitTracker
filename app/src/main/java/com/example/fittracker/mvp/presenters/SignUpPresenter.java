package com.example.fittracker.mvp.presenters;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.User;
import com.example.fittracker.mvp.contracts.SignUpContract;

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
}
