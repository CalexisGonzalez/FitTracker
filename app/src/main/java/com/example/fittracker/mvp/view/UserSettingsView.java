package com.example.fittracker.mvp.view;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onEmailClick() {
        mailView.setVisibility(View.GONE);
        mailEdit.setVisibility(View.VISIBLE);
        resetName();
        resetPassword();
        resetSurname();
    }
    private void resetEmail(){
        mailEdit.setVisibility(View.GONE);
        mailView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPasswordClick() {
        passwordView.setVisibility(View.GONE);
        passwordEdit.setVisibility(View.VISIBLE);
        resetName();
        resetSurname();
        resetEmail();
    }
    private void resetPassword(){
        passwordEdit.setVisibility(View.GONE);
        passwordView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNameClick() {
        nameView.setVisibility(View.GONE);
        nameEdit.setVisibility(View.VISIBLE);
        resetSurname();
        resetEmail();
        resetPassword();
    }
    private void resetName(){
        nameEdit.setVisibility(View.GONE);
        nameView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSurnameClick() {
        surnameView.setVisibility(View.GONE);
        surnameEdit.setVisibility(View.VISIBLE);
        resetEmail();
        resetPassword();
        resetName();
    }
    private void resetSurname(){
        surnameEdit.setVisibility(View.GONE);
        surnameView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onApplyChangesClick() {
        Toast.makeText(activity.get(),R.string.msg_on_changes_applied,Toast.LENGTH_LONG).show();
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

    @Override
    public void printError() {
        Toast.makeText(activity.get(), R.string.error_usersettings_dberror, Toast.LENGTH_SHORT).show();
    }
}
