package com.example.fittracker.mvp.presenter;

import com.example.fittracker.BuildConfig;
import com.example.fittracker.ConstantUtils;
import com.example.fittracker.adapter.MarvelAdapter;
import com.example.fittracker.mvp.contract.AvatarListContract;
import com.example.fittracker.services.marvel.MarvelPojo;
import com.example.fittracker.services.marvel.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvatarListPresenter implements AvatarListContract.Presenter {
    private AvatarListContract.Model model;
    private AvatarListContract.View view;
    private Call<MarvelPojo> call;
    private List<Result> results;

    public AvatarListPresenter(AvatarListContract.Model model, AvatarListContract.View view) {
        this.model = model;
        this.view = view;
        init();
    }

    @Override
    public void onBackPressed() {
        view.onBackPressed();
    }

    public void init() {
        call = model.getMarvelDataFromService(ConstantUtils.MARVEL_API_LIMIT, BuildConfig.marvel_api_key_public,
                BuildConfig.marvel_timestamp, BuildConfig.marvel_hash);
        call.enqueue(new Callback<MarvelPojo>() {
            @Override
            public void onResponse(Call<MarvelPojo> call, Response<MarvelPojo> response) {
                results = response.body().getData().getResults();
                view.setAdapter(new MarvelAdapter(results));
            }

            @Override
            public void onFailure(Call<MarvelPojo> call, Throwable t) {
                return;
            }
        });
    }
}
