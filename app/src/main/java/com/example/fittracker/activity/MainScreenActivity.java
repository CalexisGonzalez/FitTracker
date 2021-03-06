package com.example.fittracker.activity;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.fittracker.BusProvider;
import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.mvp.model.MainScreenModel;
import com.example.fittracker.mvp.presenter.MainScreenPresenter;
import com.example.fittracker.mvp.view.MainScreenView;
import com.example.fittracker.services.weather.WeatherGenerator;
import com.example.fittracker.services.weather.WeatherService;
import com.example.fittracker.services.workout.WorkoutGenerator;
import com.example.fittracker.services.workout.WorkoutService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainScreenActivity extends AppCompatActivity {
    private MainScreenContract.Presenter presenter;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        presenter = new MainScreenPresenter(new MainScreenView(this),
                new MainScreenModel(WeatherGenerator.createService(WeatherService.class),
                        WorkoutGenerator.createService(WorkoutService.class)));
    }

    @OnClick(R.id.mainscreen_activity_button_settings)
    public void onSettingsPressed() {
        presenter.onSettingsPressed();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @OnClick(R.id.main_screen_button_log_out)
    public void onLogOutPressed() {
        presenter.onLogOutPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onRequestPermissionResult(requestCode, permissions, grantResults);
    }

    @OnClick(R.id.mainscreen_cardview)
    public void onWeatherCardPressed() {
        presenter.onWeatherCardPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }
}
