package com.example.fittracker.mvp.model;

import com.example.fittracker.mvp.contract.MarvelDialogContract;

public class MarvelDialogModel implements MarvelDialogContract.Model {
    private String imageUrl;

    public MarvelDialogModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }
}
