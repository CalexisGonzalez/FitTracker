package com.example.fittracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fittracker.R;
import com.example.fittracker.mvp.contracts.MainScreenContract;
import com.example.fittracker.mvp.models.MainScreenModel;
import com.example.fittracker.mvp.presenters.MainScreenPresenter;
import com.example.fittracker.mvp.views.MainScreenView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainScreenActivity extends AppCompatActivity {
    private MainScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);
        init();
    }
    public void init(){
        MainScreenContract.View view = new MainScreenView(this);
        MainScreenContract.Model model = new MainScreenModel();
        presenter = new MainScreenPresenter(view,model);
    }
    @OnClick(R.id.mainscreen_activity_button_settings)
    public void onSettingsPressed(){ presenter.onSettingsPressed();}
}
