package com.example.fittracker.mvp.view;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.UserSettingsContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSettingsView implements UserSettingsContract.View {
    @BindView(R.id.usersettings_activity_textview_surname) TextView surnameView;
    @BindView(R.id.usersettings_activity_edittext_surname) EditText surnameEdit;
    @BindView(R.id.usersettings_activity_textview_name) TextView nameView;
    @BindView(R.id.usersettings_activity_edittext_name) EditText nameEdit;
    @BindView(R.id.usersettings_activity_textview_email) TextView mailView;
    @BindView(R.id.usersettings_activity_edittext_email) EditText mailEdit;
    @BindView(R.id.usersettings_activity_textview_password) TextView passwordView;
    @BindView(R.id.usersettings_activity_edittext_password) EditText passwordEdit;
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
    public void onEmailFocusChange() {

    }

    @Override
    public void onPasswordFocusChange() {

    }

    @Override
    public void onNameFocusChange() {

    }

    @Override
    public void onSurnameFocusChange() {

    }

    @Override
    public void onApplyChangesClick() {

    }

    @Override
    public void onCancelClick() {

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
    public void onCreate(String mail, String password, String name, String surname) {
        mailEdit.setText(mail);
        mailView.setText(mail);
        passwordEdit.setText(password);
        passwordView.setText(password);
        nameEdit.setText(name);
        nameView.setText(name);
        surnameEdit.setText(surname);
        surnameView.setText(surname);
    }
}
