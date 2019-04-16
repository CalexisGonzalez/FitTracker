package com.example.fittracker.mvp.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.activity.UserSettingsActivity;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreenView implements MainScreenContract.View {
    private WeakReference<Activity> activity;
    @BindView(R.id.mainscreen_cadview_city) TextView cityView;
    @BindView(R.id.mainscreen_cardview_temperature) TextView temperatureView;
    @BindView(R.id.mainscreen_cardview_icon)
    ImageView weatherIconView;
    @BindView(R.id.mainscreen_cardview_weather_status) TextView weatherStatusView;

    public MainScreenView(Activity activity) {
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
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
    public void setIconView(String url) {
        Picasso.get().load(ConstantUtils.WEATHER_ICON_URL + url + ConstantUtils.PNG_EXTENSION).
                resize(ConstantUtils.WEATHER_ICON_DIMENSION, ConstantUtils.WEATHER_ICON_DIMENSION).into(weatherIconView);
    }

    @Override
    public void setWeatherMainView(String weatherMain) {
        weatherStatusView.setText(weatherMain);
    }
}
