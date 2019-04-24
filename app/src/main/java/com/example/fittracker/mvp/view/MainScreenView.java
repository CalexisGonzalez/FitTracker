package com.example.fittracker.mvp.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.activity.UserSettingsActivity;
import com.example.fittracker.mvp.contract.MainScreenContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreenView implements MainScreenContract.View {
    private WeakReference<Activity> activity;
    private boolean isWeatherCardExpanded;
    @BindView(R.id.mainscreen_cardview_city) TextView cityView;
    @BindView(R.id.mainscreen_cardview_temperature) TextView temperatureView;
    @BindView(R.id.mainscreen_cardview_icon)
    ImageView weatherIconView;
    @BindView(R.id.mainscreen_cardview_weather_status) TextView weatherStatusView;
    @BindView(R.id.mainscreen_cardview_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.mainscreen_cardview_icon_progressBar) ProgressBar iconProgressBar;
    @BindView(R.id.mainscreen_cardview_weather_humidity_tag) TextView humidityTag;
    @BindView(R.id.mainscreen_cardview_weather_humidity) TextView humidityView;
    @BindView(R.id.mainscreen_cardview_weather_pressure_tag) TextView pressureTag;
    @BindView(R.id.mainscreen_cardview_weather_pressure) TextView pressureView;

    public MainScreenView(Activity activity) {
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
        isWeatherCardExpanded = false;
    }

    @Override
    public void onSettingsPressed() {
        Intent intent = new Intent(activity.get(), UserSettingsActivity.class);
        activity.get().startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.get().startActivity(startMain);
    }

    @Override
    public void onLogOutPressed() {
        SharedPreferences.Editor editor = activity.get().
                getSharedPreferences(ConstantUtils.USER_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putInt(ConstantUtils.USER_PREFERENCES_ID, ConstantUtils.NEGATIVE_ONE);
        editor.apply();
        activity.get().finish();
    }

    @Override
    public boolean locationPermissionGranted() {
        return (ActivityCompat.checkSelfPermission(activity.get(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public LocationManager getLocationManager() {
        return (LocationManager) activity.get().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void requestPermissions() {
        ActivityCompat.requestPermissions(activity.get(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                ConstantUtils.LOCATION_REQUEST_CODE);
    }

    @Override
    public void fetchingError() {
        Toast.makeText(activity.get(), R.string.error_fetching_api_data, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCityView(String city) {
        cityView.setText(city);
    }

    @Override
    public void setTemperatureView(Double temperature) {
        String s = String.valueOf(temperature) + ConstantUtils.CELSIUS_EXTENSIONS;
        temperatureView.setText(s);
    }

    @Override
    public void setWeatherMainView(String weatherMain) {
        weatherStatusView.setText(weatherMain);
    }

    @Override
    public void onWeatherDataGet() {
        progressBar.setVisibility(View.GONE);
        iconProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public ImageView getWeatherIconView() {
        return weatherIconView;
    }

    @Override
    public void hideWeatherIconProgressBar() {
        iconProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void expandWeatherCard() {
        humidityTag.setVisibility(View.VISIBLE);
        humidityView.setVisibility(View.VISIBLE);
        pressureTag.setVisibility(View.VISIBLE);
        pressureView.setVisibility(View.VISIBLE);
        isWeatherCardExpanded = !isWeatherCardExpanded;
    }

    @Override
    public void contractWeatherCard() {
        humidityTag.setVisibility(View.GONE);
        humidityView.setVisibility(View.GONE);
        pressureTag.setVisibility(View.GONE);
        pressureView.setVisibility(View.GONE);
        isWeatherCardExpanded = !isWeatherCardExpanded;
    }

    @Override
    public boolean isWeatherCardExpanded() {
        return isWeatherCardExpanded;
    }

    @Override
    public void setHumidityView(Double humidity) {
        humidityView.setText(humidity.toString() + ConstantUtils.PERCENT);
    }

    @Override
    public void setPressureView(Double pressure) {
        pressureView.setText(pressure.toString() + ConstantUtils.PASCALS);
    }
}
