package com.example.fittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private LogInContract.Presenter presenter;
    private EditText mail;
    private EditText password;
    private UserBase lista;
    private final String MAIL_PRUEBA = "danko94.cg@gmail.com";
    private final String PASS_PRUEBA = "1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=findViewById(R.id.mail_usuario);
        password=findViewById(R.id.password);
        lista = new UserBase();
        lista.addUsuario(new User(MAIL_PRUEBA,PASS_PRUEBA));
        init();
    }
    public void init(){
        LogInContract.View view = new MainActivityView(mail,password,this);
        LogInContract.Model model = new MainActivityModel(lista);
        presenter = new MainActivityPresenter(view, model);
        final Button logIn = findViewById(R.id.log_in);
        final Button signUp = findViewById(R.id.sign_up);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogInPressed();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpPressed();
            }
        });
    }
}
