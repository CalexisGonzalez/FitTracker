package com.example.fittracker.mvp.presenters;

import com.example.fittracker.mvp.contracts.MainScreenContract;

public class MainScreenPresenter implements MainScreenContract.Presenter {
    private MainScreenContract.View view;
    private MainScreenContract.Model model;

    public MainScreenPresenter(MainScreenContract.View view, MainScreenContract.Model model){
        this.model = model;
        this.view = view;
    }

    @Override
    public void onSettingsPressed() {
        view.onSettingsPressed();
    }
}
