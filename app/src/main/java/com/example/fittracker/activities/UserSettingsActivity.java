package com.example.fittracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fittracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class UserSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        ButterKnife.bind(this);
    }

    @OnLongClick(R.id.usersettings_activity_textview_email)
    public boolean onEmailLongClick(){

        return true;
    }
    @OnLongClick(R.id.usersettings_activity_textview_password)
    public boolean onPasswordLongClick(){
        return true;
    }
    @OnLongClick(R.id.usersettings_activity_textview_nombre)
    public boolean onNameLongClick(){
        return true;
    }
    @OnLongClick(R.id.usersettings_activity_textview_apellido)
    public boolean onSurnameLongClick(){
        return true;
    }
}
