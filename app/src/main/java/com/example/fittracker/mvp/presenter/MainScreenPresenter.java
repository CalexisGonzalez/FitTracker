package com.example.fittracker.mvp.presenter;

import android.annotation.SuppressLint;
import android.location.LocationManager;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.WeatherGenerator;
import com.example.fittracker.services.WeatherPojo;
import com.example.fittracker.services.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenPresenter implements MainScreenContract.Presenter {
    private MainScreenContract.View view;
    private MainScreenContract.Model model;
    private LocationManager locationManager;
    private Call<WeatherPojo> call;
    private WeatherPojo weatherData;

    public MainScreenPresenter(MainScreenContract.View view, MainScreenContract.Model model) {
        this.model = model;
        this.view = view;
        init();
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
        model.setLocationObject(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
    }

    public void init() {
        locationManager = view.getLocationManager();
        checkLocationPermission();
        model.setWeatherService(WeatherGenerator.createService(WeatherService.class));
        call = model.getService().getData(model.getLatitud(), model.getLongitude(),
                ConstantUtils.WEATHER_API_KEY, ConstantUtils.WEATHER_UNITS);
        call.enqueue(new Callback<WeatherPojo>() {
            @Override
            public void onResponse(Call<WeatherPojo> call, Response<WeatherPojo> response) {
                weatherData = response.body();
                view.setCityView(weatherData.getName());
                view.setTemperatureView(weatherData.getMain().getTemp());
                view.setIconView(weatherData.getWeather().get(ConstantUtils.ZERO).getIcon());
                view.setWeatherMainView(weatherData.getWeather().get(ConstantUtils.ZERO).getMain());
            }

            @Override
            public void onFailure(Call<WeatherPojo> call, Throwable t) {
                view.fetchingError();
            }
        });
    }
}

