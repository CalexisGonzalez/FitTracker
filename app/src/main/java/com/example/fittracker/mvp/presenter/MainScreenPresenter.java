package com.example.fittracker.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;

import com.example.fittracker.BuildConfig;
import com.example.fittracker.ConstantUtils;
import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.WeatherPojo;
import com.squareup.picasso.Picasso;

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
        call = model.getWeatherDataFromService(model.getLatitud(), model.getLongitude(),
                BuildConfig.weather_api_key, ConstantUtils.WEATHER_UNITS);
        call.enqueue(new Callback<WeatherPojo>() {
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
        Picasso.get().load(BuildConfig.weather_icon_url + url + ConstantUtils.PNG_EXTENSION)
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
        if (view.isWeatherCardExpanded()){
            view.contractWeatherCard();
        }else{
            view.expandWeatherCard();
        }
    }
}

