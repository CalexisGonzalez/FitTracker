package com.example.fittracker.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;

import com.example.fittracker.BuildConfig;
import com.example.fittracker.ConstantUtils;
import com.example.fittracker.adapter.WorkoutAdapter;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.weather.WeatherPojo;
import com.example.fittracker.services.workout.Result;
import com.example.fittracker.services.workout.WorkoutPojo;
import com.example.fittracker.services.workout.WorkoutUnit;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenPresenter implements MainScreenContract.Presenter {
    private MainScreenContract.View view;
    private MainScreenContract.Model model;
    private LocationManager locationManager;
    private Call<WeatherPojo> weatherCall;
    private Call<WorkoutPojo> workoutCall;
    private WeatherPojo weatherData;
    private List<WorkoutUnit> workoutUnits;
    private float steps;

    public MainScreenPresenter(MainScreenContract.View view, MainScreenContract.Model model) {
        this.model = model;
        this.view = view;
        initWeather();
        initExercise();
    }

    @Override
    public void onSettingsPressed() {
        view.onSettingsPressed();
    }

    @Override
    public void onBackPressed() {
        view.onBackPressed();
    }

    @Override
    public void onLogOutPressed() {
        model.setSharedPreferencesSteps(ConstantUtils.NEGATIVE_ONE);
        model.setSharedPreferencesUser(ConstantUtils.ZERO);
        view.onLogOutPressed();
    }

    @Override
    public void checkLocationPermission() {
        if (!view.locationPermissionGranted()) {
            view.requestPermissions();
        } else {
            setLocationObject();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void setLocationObject() {
        model.setLocationObject(getLastKnownLocation());
    }

    private Location getLastKnownLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    public void initWeather() {
        locationManager = view.getLocationManager();
        checkLocationPermission();
        weatherCall = model.getWeatherDataFromService(model.getLatitud(), model.getLongitude(),
                BuildConfig.WEATHER_API_KEY, ConstantUtils.WEATHER_UNITS);
        weatherCall.enqueue(new Callback<WeatherPojo>() {
            @Override
            public void onResponse(Call<WeatherPojo> call, Response<WeatherPojo> response) {
                view.onWeatherDataGet();
                weatherData = response.body();
                view.setCityView(weatherData.getName());
                view.setTemperatureView(weatherData.getMain().getTemp());
                setWeatherIcon(weatherData.getWeather().get(ConstantUtils.ZERO).getIcon());
                view.setWeatherMainView(weatherData.getWeather().get(ConstantUtils.ZERO).getMain());
                view.setHumidityView(weatherData.getMain().getHumidity());
                view.setPressureView(weatherData.getMain().getPressure());
            }

            @Override
            public void onFailure(Call<WeatherPojo> call, Throwable t) {
                view.fetchingError();
            }
        });
    }

    public void initExercise() {
        workoutCall = model.getWorkoutDataFromService(ConstantUtils.WORKOUT_API_LIMIT,
                ConstantUtils.WORKOUT_API_FORMAT);
        workoutCall.enqueue(new Callback<WorkoutPojo>() {
            @Override
            public void onResponse(Call<WorkoutPojo> call, Response<WorkoutPojo> response) {
                workoutUnits = getWorkoutUnits(response.body().getResults());
                view.setAdapter(new WorkoutAdapter(workoutUnits));
            }

            @Override
            public void onFailure(Call<WorkoutPojo> call, Throwable t) {
                view.fetchingError();
            }
        });
    }

    @Override
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > ConstantUtils.ZERO
                && grantResults[ConstantUtils.ZERO] == PackageManager.PERMISSION_GRANTED
                && requestCode == ConstantUtils.LOCATION_REQUEST_CODE) {
            setLocationObject();
        } else {
            checkLocationPermission();
        }
    }

    @Override
    public void setWeatherIcon(String url) {
        Picasso.get().load(BuildConfig.WEATHER_ICON_URL + url + ConstantUtils.PNG_EXTENSION)
                .resize(ConstantUtils.WEATHER_ICON_DIMENSION, ConstantUtils.WEATHER_ICON_DIMENSION)
                .into(view.getWeatherIconView(), new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        view.hideWeatherIconProgressBar();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public void onWeatherCardPressed() {
        if (view.isWeatherCardExpanded()) {
            view.contractWeatherCard();
        } else {
            view.expandWeatherCard();
        }
    }

    private List<WorkoutUnit> getWorkoutUnits(List<Result> results) {
        List<WorkoutUnit> units = new ArrayList<>();
        for (int i = ConstantUtils.ZERO; i < ConstantUtils.WORKOUT_API_LIMIT / ConstantUtils.FOUR; i++) {
            units.add(new WorkoutUnit(results.subList(i * ConstantUtils.FOUR,
                    (i + ConstantUtils.ONE) * ConstantUtils.FOUR)));
        }
        return units;
    }

    @Subscribe
    public void onImageError(Exception e) {
        view.onImageError();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        steps = event.values[ConstantUtils.ZERO];
        if (!model.existsSharedPreferencesSteps() || model.getSharedPreferencesSteps() == ConstantUtils.NEGATIVE_ONE) {
            model.setSharedPreferencesSteps(steps);
        }
        view.setStepCountView((int) steps - model.getSharedPreferencesSteps());
        view.setStepDistanceView((steps - model.getSharedPreferencesSteps()) * ConstantUtils.STEPS_TO_KILOMETERS);
        view.setStepCaloriesView((steps - model.getSharedPreferencesSteps()) * ConstantUtils.STEPS_TO_CALORIES);
    }

    @Override
    public void onResetStepsPressed() {
        model.setSharedPreferencesSteps(steps);
        view.onResetStepsPressed();
    }
}

