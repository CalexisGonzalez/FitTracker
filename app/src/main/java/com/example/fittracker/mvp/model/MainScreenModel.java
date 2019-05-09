package com.example.fittracker.mvp.model;

import android.content.SharedPreferences;
import android.location.Location;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.weather.WeatherPojo;
import com.example.fittracker.services.weather.WeatherService;
import com.example.fittracker.services.workout.WorkoutPojo;
import com.example.fittracker.services.workout.WorkoutService;

import retrofit2.Call;

public class MainScreenModel implements MainScreenContract.Model {
    private Location location;
    private WeatherService weatherService;
    private WorkoutService workoutService;
    private SharedPreferences preferences;

    public MainScreenModel(WeatherService weatherService, WorkoutService workoutService, SharedPreferences preferences) {
        this.weatherService = weatherService;
        this.workoutService = workoutService;
        this.preferences = preferences;
    }

    @Override
    public void setLocationObject(Location location) {
        this.location = location;
    }


    @Override
    public double getLatitud() {
        return location.getLatitude();
    }

    @Override
    public double getLongitude() {
        return location.getLongitude();
    }

    @Override
    public WeatherService getWeatherService() {
        return weatherService;
    }

    @Override
    public WorkoutService getWorkoutService() {
        return workoutService;
    }

    @Override
    public Call<WeatherPojo> getWeatherDataFromService(double lat, double lon, String appid, String units) {
        return getWeatherService().getData(lat, lon, appid, units);
    }

    @Override
    public Call<WorkoutPojo> getWorkoutDataFromService(int limit, String format) {
        return getWorkoutService().getData(limit, format);
    }

    @Override
    public int getSharedPreferencesSteps() {
        return preferences.getInt(ConstantUtils.USER_PREFERENCES_STEPS, ConstantUtils.ZERO);
    }

    @Override
    public boolean existsSharedPreferencesSteps() {
        return preferences.contains(ConstantUtils.USER_PREFERENCES_STEPS);
    }

    @Override
    public void setSharedPreferencesSteps(float steps) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ConstantUtils.USER_PREFERENCES_STEPS, (int) steps);
        editor.apply();
    }

    @Override
    public void setSharedPreferencesUser(int id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ConstantUtils.USER_PREFERENCES_ID, id);
        editor.apply();
    }
}
