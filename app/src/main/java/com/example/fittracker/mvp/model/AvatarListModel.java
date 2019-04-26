package com.example.fittracker.mvp.model;

import com.example.fittracker.mvp.contract.AvatarListContract;
import com.example.fittracker.services.marvel.MarvelPojo;
import com.example.fittracker.services.marvel.MarvelService;

import retrofit2.Call;

public class AvatarListModel implements AvatarListContract.Model {
    private MarvelService service;

    public AvatarListModel(MarvelService service) {
        this.service = service;
    }

    @Override
    public Call<MarvelPojo> getMarvelDataFromService(int limit, String apikey, int ts, String hash) {
        return service.getData(limit, apikey, ts, hash);
    }
}
