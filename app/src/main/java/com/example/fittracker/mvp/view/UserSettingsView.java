package com.example.fittracker.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.GMailSender;
import com.example.fittracker.R;
import com.example.fittracker.activity.AvatarListActivity;
import com.example.fittracker.mvp.contract.UserSettingsContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSettingsView implements UserSettingsContract.View {
    @BindView(R.id.usersettings_activity_edittext_surname) EditText surnameEdit;
    @BindView(R.id.usersettings_activity_edittext_name) EditText nameEdit;
    @BindView(R.id.usersettings_activity_edittext_email) EditText mailEdit;
    @BindView(R.id.usersettings_activity_edittext_password) EditText passwordEdit;
    @BindView(R.id.usersettings_activity_chckbox_sendmail)
    CheckBox mailBox;
    private WeakReference<Activity> activity;

    public UserSettingsView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void onEmailClick() {
        mailEdit.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    @Override
    public void onPasswordClick() {
        passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onNameClick() {
        nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
    }

    @Override
    public void onSurnameClick() {
        surnameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
    }

    @Override
    public void onApplyChangesClick() {
        Toast.makeText(activity.get(), R.string.msg_on_changes_applied, Toast.LENGTH_LONG).show();
        activity.get().finish();
    }

    @Override
    public void onCancelClick() {
        activity.get().finish();
    }

    @Override
    public String getName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getSurname() {
        return surnameEdit.getText().toString();
    }

    @Override
    public String getMail() {
        return mailEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEdit.getText().toString();
    }

    @Override
    public void initMail(String mail) {
        mailEdit.setText(mail);
    }

    @Override
    public void initPassword(String password) {
        passwordEdit.setText(password);
    }

    @Override
    public void initName(String name) {
        nameEdit.setText(name);
    }

    @Override
    public void initSurname(String surname) {
        surnameEdit.setText(surname);
    }

    @Override
    public void printFetchingError() {
        Toast.makeText(activity.get(), R.string.error_usersettings_dberror, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printMissingFieldError() {
        Toast.makeText(activity.get(), R.string.error_signup_missing_field, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printInvalidEmail() {
        Toast.makeText(activity.get(), R.string.error_emailformat, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkBoxMailPressed() {
        return mailBox.isChecked();
    }

    @Override
    public void onSendMailPressed(final GMailSender sender, final String subject, final String body, final String recipient) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    sender.sendMail(subject, body, sender.getSenderUser(), recipient);
                } catch (Exception e) {
                    Log.e(ConstantUtils.SENDMAIL_LOG, e.getMessage(), e);
                }
            }

        }).start();
    }

    @Override
    public void onChangeAvatarClicked() {
        Intent intent = new Intent(activity.get(), AvatarListActivity.class);
        activity.get().startActivity(intent);
    }
}
