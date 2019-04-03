package com.example.fittracker.mvp.contract;

public interface MainScreenContract {
    interface Presenter {
        void onSettingsPressed();
    }

    interface Model {

    }

    interface View {
        void onSettingsPressed();
    }
}
