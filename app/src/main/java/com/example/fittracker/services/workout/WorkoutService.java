package com.example.fittracker.services.workout;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorkoutService {
    @GET("/api/v2/exerciseimage/")
    Call<WorkoutPojo> getData(@Query("limit") int limit,
                             @Query("format") String format);
}
