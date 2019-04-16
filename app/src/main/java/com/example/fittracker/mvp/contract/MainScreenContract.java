package com.example.fittracker.mvp.contract;

import android.location.Location;
import android.location.LocationManager;

import com.example.fittracker.services.WeatherService;

public interface MainScreenContract {
    interface Presenter {
        void onSettingsPressed();

        void onBackPressed();

        void onLogOutPressed();

        void checkLocationPermission();

        void setLocationObject();
    }

    interface Model {
        void setLocationObject(Location location);

        double getLatitud();

        double getLongitude();

        WeatherService getService();

        void setWeatherService(WeatherService service);
    }

    interface View {
        void onSettingsPressed();

        void onBackPressed();

        void onLogOutPressed();

        boolean locationPermissionGranted();

        LocationManager getLocationManager();

        void requestPermissions();

        void fetchingError();

        void setCityView(String city);

        void setTemperatureView(Double temperature);

        void setIconView(String url);

        void setWeatherMainView(String weatherMain);
    }
}
