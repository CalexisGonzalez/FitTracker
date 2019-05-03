package com.example.fittracker.mvp.contract;

import android.widget.ImageView;

import com.example.fittracker.GMailSender;
import com.example.fittracker.User;

public interface UserSettingsContract {
    interface Model {
        public void onApplyChanges(User user);

        public User getUserData(int id);

        public int getLoggedUserId();

        public String getImageUrl(int id);
    }

    interface View {
        public void onEmailClick();

        public void onPasswordClick();

        public void onNameClick();

        public void onSurnameClick();

        public void onApplyChangesClick();

        public void onCancelClick();

        public String getName();

        public String getSurname();

        public String getMail();

        public String getPassword();

        public void initMail(String mail);

        public void initPassword(String password);

        public void initName(String name);

        public void initSurname(String surname);

        public void onSendMailPressed(GMailSender sender, String subject, String body, String recipient);

        public void printFetchingError();

        public void printMissingFieldError();

        public void printInvalidEmail();

        public boolean checkBoxMailPressed();

        public void onChangeAvatarClicked();

        public ImageView getAvatarImageView();

        public void onImageFetchingError();

        public void hideIconProgressBar();
    }

    interface Presenter {
        public void onEmailClick();

        public void onPasswordClick();

        public void onNameClick();

        public void onSurnameClick();

        public void onApplyChangesClick();

        public void onCancelClick();

        public void init();

        public void onSendMailPressed();

        public void onChangeAvatarClicked();

        public void loadIconView();
    }
}
