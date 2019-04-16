package com.example.fittracker.mvp.model;

import android.location.Location;

import com.example.fittracker.mvp.contract.MainScreenContract;
import com.example.fittracker.services.WeatherService;

public class MainScreenModel implements MainScreenContract.Model {
    private Location location;
    private WeatherService service;

    public MainScreenModel() {
    }

    @Override
    public void setLocationObject(Location location) {
        this.location = location;
    }

    @Override
    public void setWeatherService(WeatherService service) {
        this.service = service;
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
}
