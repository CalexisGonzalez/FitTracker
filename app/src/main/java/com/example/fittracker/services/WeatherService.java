package com.example.fittracker.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/weather")
    Call<WeatherPojo> getData(@Query("lat") double lat, @Query("lon") double lon,
                              @Query("APPID") String appid, @Query("units") String units);
}
