package com.example.fittracker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.fittracker.R;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contracts.SignUpContract;
import com.example.fittracker.mvp.models.SignUpModel;
import com.example.fittracker.mvp.presenters.SignUpPresenter;
import com.example.fittracker.mvp.views.SignUpView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {
    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        SignUpContract.Model model = new SignUpModel(UserRoomDatabase.getDatabase(this));
        SignUpContract.View view = new SignUpView(this);
        presenter = new SignUpPresenter(model, view);
    }

    @OnClick(R.id.signup_activity_button_cancel)
    public void onCancelPressed() {
        presenter.onCancelPressed();
    }

    @OnClick(R.id.signup_activity_button_signup)
    public void onSignUpPressed() {
        presenter.onSignUpPressed();
    }
}
