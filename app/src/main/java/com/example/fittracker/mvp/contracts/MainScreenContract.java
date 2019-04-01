package com.example.fittracker.mvp.contracts;

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
