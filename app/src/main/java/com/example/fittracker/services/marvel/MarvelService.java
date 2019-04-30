package com.example.fittracker.services.marvel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {
    @GET("/v1/public/characters")
    Call<MarvelPojo> getData(@Query("limit") int limit, @Query("apikey") String apikey,
                             @Query("ts") int ts, @Query("hash") String hash);
}
