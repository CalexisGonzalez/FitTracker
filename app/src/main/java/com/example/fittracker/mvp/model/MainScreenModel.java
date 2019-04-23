package com.example.fittracker.mvp.model;

import android.location.Location;

import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.WeatherPojo;
import com.example.fittracker.services.WeatherService;

import retrofit2.Call;

public class MainScreenModel implements MainScreenContract.Model {
    private Location location;
    private WeatherService service;

    public MainScreenModel(WeatherService service) {
        this.service = service;
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
    public WeatherService getService() {
        return service;
    }

    @Override
    public Call<WeatherPojo> getWeatherDataFromService(double lat, double lon, String appid, String units) {
        return getService().getData(lat, lon, appid, units);
    }
}
