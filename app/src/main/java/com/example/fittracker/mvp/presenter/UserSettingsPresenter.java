package com.example.fittracker.mvp.presenter;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.GMailSender;
import com.example.fittracker.User;
import com.example.fittracker.mvp.contract.UserSettingsContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSettingsPresenter implements UserSettingsContract.Presenter {
    private UserSettingsContract.View view;
    private UserSettingsContract.Model model;
    private int userId;
    private User user;

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
        } else if (!isEmailValid(view.getMail())) {
            view.printInvalidEmail();
        } else {
            User user = new User(view.getMail(), view.getPassword(), view.getName(), view.getSurname(),
                    ConstantUtils.DEFAULT_AVATAR_URL);
            user.setId(userId);
            model.onApplyChanges(user);
            if (view.checkBoxMailPressed()) {
                onSendMailPressed();
            }
            view.onApplyChangesClick();
        }
    }

    @Override
    public void onCancelClick() {
        view.onCancelClick();
    }

    @Override
    public void init() {
        user = model.getUserData(userId);
        if (user != null) {
            view.initMail(user.getMail());
            view.initPassword(user.getPassword());
            view.initName(user.getName());
            view.initSurname(user.getSurname());
            loadIconView();
        } else {
            view.printFetchingError();
        }
    }

    @Override
    public void onSendMailPressed() {
        GMailSender sender = new GMailSender(ConstantUtils.EMAIL_SENDER, ConstantUtils.PASSWORD_SENDER);
        String body = view.getMail() + System.lineSeparator() + view.getPassword() + System.lineSeparator() +
                view.getName() + System.lineSeparator() + view.getSurname();
        view.onSendMailPressed(sender, ConstantUtils.EMAIL_SUBJECT, body, view.getMail());
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

    @Override
    public void onChangeAvatarClicked() {
        view.onChangeAvatarClicked();
    }

    @Override
    public void loadIconView() {
        Picasso.get().load(model.getImageUrl(userId)).resize(ConstantUtils.MARVEL_IMAGE_SMALL_DIMENSION,
                ConstantUtils.MARVEL_IMAGE_SMALL_DIMENSION).into(view.getAvatarImageView(), new Callback() {
            @Override
            public void onSuccess() {
                view.hideIconProgressBar();
            }

            @Override
            public void onError(Exception e) {
                view.onImageFetchingError();
            }
        });
    }
}
