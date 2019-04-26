package com.example.fittracker.mvp.contract;

import com.example.fittracker.adapter.MarvelAdapter;
import com.example.fittracker.services.marvel.MarvelPojo;

import retrofit2.Call;

public interface AvatarListContract {
    interface Presenter {
        void onBackPressed();
    }

    interface Model {
        Call<MarvelPojo> getMarvelDataFromService(int limit, String apikey, int ts, String hash);
    }

    interface View {
        void onBackPressed();

        void setAdapter(MarvelAdapter adapter);
    }
}
