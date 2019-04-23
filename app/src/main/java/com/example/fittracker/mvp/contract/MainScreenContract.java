package com.example.fittracker.mvp.contract;

import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.fittracker.services.WeatherPojo;
import com.example.fittracker.services.WeatherService;

import retrofit2.Call;

public interface MainScreenContract {
    interface Presenter {
        void onSettingsPressed();

        void onBackPressed();

        void onLogOutPressed();

        void checkLocationPermission();

        void setLocationObject();

        void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

        void setWeatherIcon(String url);
    }

    interface Model {
        void setLocationObject(Location location);

        double getLatitud();

        double getLongitude();

        WeatherService getService();

        Call<WeatherPojo> getWeatherDataFromService(double lat, double lon, String appid, String units);
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

        void hideWeatherIconProgressBar();

        void setWeatherMainView(String weatherMain);

        void onWeatherDataGet();

        ImageView getWeatherIconView();
    }
}
