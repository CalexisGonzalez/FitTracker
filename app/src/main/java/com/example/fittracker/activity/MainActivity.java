package com.example.fittracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fittracker.MainActivityView;
import com.example.fittracker.R;
import com.example.fittracker.UserRoomDatabase;
import com.example.fittracker.mvp.contract.LogInContract;
import com.example.fittracker.mvp.model.MainActivityModel;
import com.example.fittracker.mvp.presenter.MainActivityPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private LogInContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        LogInContract.View view = new MainActivityView(this);
        LogInContract.Model model = new MainActivityModel(UserRoomDatabase.getDatabase(this));
        presenter = new MainActivityPresenter(view, model);
    }

    @OnClick(R.id.main_activity_button_log_in)
    public void logIn() {
        presenter.onLogInPressed();
    }

    @OnClick(R.id.main_activity_button_sign_up)
    public void signUp() {
        presenter.onSignUpPressed();
    }
}
