package com.example.fittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private LogInContract.Presenter presenter;
    private final String MAIL_PRUEBA = "danko94.cg@gmail.com";
    private final String PASS_PRUEBA = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        LogInContract.View view = new MainActivityView(this);
        UserBase list = new UserBase();
        list.addUsuario(new User(MAIL_PRUEBA, PASS_PRUEBA));
        LogInContract.Model model = new MainActivityModel(list);
        presenter = new MainActivityPresenter(view, model);
    }

    @OnClick(R.id.log_in)
    public void logIn() {
        presenter.onLogInPressed();
    }

    @OnClick(R.id.sign_up)
    public void signUp() {
        presenter.onSignUpPressed();
    }
}
