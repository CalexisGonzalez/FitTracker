package com.example.fittracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.UserSettingsContract;
import com.example.fittracker.mvp.model.UserSettingsModel;
import com.example.fittracker.mvp.presenter.UserSettingsPresenter;
import com.example.fittracker.mvp.view.UserSettingsView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSettingsActivity extends AppCompatActivity {
    private UserSettingsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        presenter = new UserSettingsPresenter(new UserSettingsView(this)
                ,new UserSettingsModel(UserRoomDatabase.getDatabase(this)
                ,getSharedPreferences(ConstantUtils.USER_PREFERENCES, MODE_PRIVATE)));
        presenter.init();
    }

    @OnClick(R.id.usersettings_activity_edittext_email)
    public void onEmailFocusChange() {
        presenter.onEmailClick();
    }

    @OnClick(R.id.usersettings_activity_edittext_password)
    public void onPasswordFocusChange() {
        presenter.onPasswordClick();
    }

    @OnClick(R.id.usersettings_activity_edittext_name)
    public void onNameFocusChange() {
        presenter.onNameClick();
    }

    @OnClick(R.id.usersettings_activity_edittext_surname)
    public void onSurnameFocusChange() {
        presenter.onSurnameClick();
    }

    @OnClick(R.id.usersettings_activity_button_apply_changes)
    public void onApplyChangesClicked() {
        presenter.onApplyChangesClick();
    }

    @OnClick(R.id.usersettings_activity_button_cancel)
    public void onCancelClicked() {
        presenter.onCancelClick();
    }

    @OnClick(R.id.usersettings_activity_button_sendmail)
    public void onSendMailClick() { presenter.onSendMailClick();}
}
