package com.example.fittracker.mvp.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.activity.UserSettingsActivity;
import com.example.fittracker.adapter.WorkoutAdapter;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.workout.WorkoutUnit;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreenView implements MainScreenContract.View {
    private WeakReference<Activity> activity;
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
    @BindView(R.id.mainscreen_activity_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.mainscreen_activity_recyclerview_progressbar) ProgressBar
            workoutProgressBar;
    @BindView(R.id.mainscreen_step_count) TextView stepCountView;
    @BindView(R.id.mainscreen_step_distance) TextView stepDistanceView;
    @BindView(R.id.mainscreen_step_calories) TextView stepCaloriesView;

    public MainScreenView(Activity activity) {
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
        init();
    }

    public void init() {
        recyclerView.setAdapter(new WorkoutAdapter(new ArrayList<WorkoutUnit>()));
        LinearLayoutManager llm = new LinearLayoutManager(this.activity.get());
        recyclerView.setLayoutManager(llm);
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
        activity.get().finish();
    }

    @Override
    public boolean locationPermissionGranted() {
        boolean isGranted = ((ContextCompat.checkSelfPermission(activity.get(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(activity.get(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED));
        return isGranted;
    }

    @Override
    public LocationManager getLocationManager() {
        return (LocationManager) activity.get().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void requestPermissions() {
        ActivityCompat.requestPermissions(activity.get(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
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
    }

    @Override
    public void contractWeatherCard() {
        humidityTag.setVisibility(View.GONE);
        humidityView.setVisibility(View.GONE);
        pressureTag.setVisibility(View.GONE);
        pressureView.setVisibility(View.GONE);
    }

    @Override
    public boolean isWeatherCardExpanded() {
        return humidityView.getVisibility() != View.GONE;
    }

    @Override
    public void setHumidityView(Double humidity) {
        humidityView.setText(humidity.toString() + ConstantUtils.PERCENT);
    }

    @Override
    public void setPressureView(Double pressure) {
        pressureView.setText(pressure.toString() + ConstantUtils.PASCALS);
    }

    @Override
    public void setAdapter(WorkoutAdapter adapter) {
        workoutProgressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
    }

    @Override
    public void onImageError() {
        Toast.makeText(activity.get(), R.string.error_fetching_image, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setStepCountView(int steps) {
        stepCountView.setText(String.valueOf(steps));
    }

    @Override
    public void setStepDistanceView(float distance) {
        stepDistanceView.setText(String.format(ConstantUtils.FLOAT_FORMAT, distance));
    }

    @Override
    public void setStepCaloriesView(float calories) {
        stepCaloriesView.setText(String.format(ConstantUtils.FLOAT_FORMAT, calories));
    }

    @Override
    public void onResetStepsPressed() {
        Toast.makeText(activity.get(), R.string.msg_on_steps_reset, Toast.LENGTH_SHORT).show();
        setStepCaloriesView(ConstantUtils.ZERO);
        setStepCountView(ConstantUtils.ZERO);
        setStepDistanceView(ConstantUtils.ZERO);
    }
}
