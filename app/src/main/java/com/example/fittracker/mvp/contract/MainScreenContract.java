package com.example.fittracker.mvp.contract;

import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.fittracker.adapter.WorkoutAdapter;
import com.example.fittracker.services.weather.WeatherPojo;
import com.example.fittracker.services.weather.WeatherService;
import com.example.fittracker.services.workout.WorkoutPojo;
import com.example.fittracker.services.workout.WorkoutService;

import retrofit2.Call;

public interface MainScreenContract {
    interface Presenter extends SensorEventListener {
        void onSettingsPressed();

        void onBackPressed();

        void onLogOutPressed();

        void checkLocationPermission();

        void setLocationObject();

        void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

        void setWeatherIcon(String url);

        void onWeatherCardPressed();

        void onResetStepsPressed();
    }

    interface Model {
        void setLocationObject(Location location);

        double getLatitud();

        double getLongitude();

        WeatherService getWeatherService();

        WorkoutService getWorkoutService();

        Call<WeatherPojo> getWeatherDataFromService(double lat, double lon, String appid, String units);

        Call<WorkoutPojo> getWorkoutDataFromService(int limit, String format);

        int getSharedPreferencesSteps();

        boolean existsSharedPreferencesSteps();

        void setSharedPreferencesSteps(float steps);

        void setSharedPreferencesUser(int id);
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

        void expandWeatherCard();

        void contractWeatherCard();

        boolean isWeatherCardExpanded();

        void setHumidityView(Double humidity);

        void setPressureView(Double pressure);

        void setAdapter(WorkoutAdapter adapter);

        void onImageError();

        void setStepCountView(int steps);

        void setStepDistanceView(float distance);

        void setStepCaloriesView(float calories);

        void onResetStepsPressed();
    }
}
