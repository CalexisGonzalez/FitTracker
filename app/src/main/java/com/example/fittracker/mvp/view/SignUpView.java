package com.example.fittracker.mvp.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.GMailSender;
import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.SignUpContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpView implements SignUpContract.View {
    private WeakReference<Activity> activity;
    @BindView(R.id.signup_activity_text_email) EditText mail;
    @BindView(R.id.signup_activity_text_password) EditText password;
    @BindView(R.id.signup_activity_text_pass_rep) EditText passwordRep;
    @BindView(R.id.signup_activity_text_nombre) EditText name;
    @BindView(R.id.signup_activity_text_apellido) EditText surname;
    @BindView(R.id.signup_activity_chckbox_sendmail)
    CheckBox mailBox;

    public SignUpView(Activity activity) {
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
    }

    @Override
    public String getEmail() {
        return mail.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public void userAlreadyExists() {
        Toast.makeText(activity.get(), R.string.error_user_already_exists, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void succesfulSignUp() {
        Toast.makeText(activity.get(), R.string.msg_on_valid_signup, Toast.LENGTH_SHORT).show();
        activity.get().finish();
    }

    @Override
    public void missingFieldError() {
        Toast.makeText(activity.get(), R.string.error_signup_missing_field, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passwordRepMatchError() {
        Toast.makeText(activity.get(),R.string.error_passwordrep_badmatch, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getRepPassword() {
        return passwordRep.getText().toString();
    }

    @Override
    public String getSurname() {
        return surname.getText().toString();
    }

    @Override
    public void onCancel() {
        activity.get().finish();
    }

    @Override
    public void emailFormatError() {
        Toast.makeText(activity.get(),R.string.error_emailformat ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendMailPressed(final GMailSender sender,final String subject,final String body, final String recipient) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    sender.sendMail(subject,body,sender.getSenderUser(),recipient);
                } catch (Exception e) {
                    Log.e(ConstantUtils.SENDMAIL_LOG, e.getMessage(), e);
                }
            }

        }).start();
    }

    @Override
    public boolean checkboxMailPressed() {
        return mailBox.isChecked();
    }
}
