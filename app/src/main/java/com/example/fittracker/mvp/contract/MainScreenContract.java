package com.example.fittracker.mvp.contract;

public interface MainScreenContract {
    interface Presenter {
        void onSettingsPressed();
        void onBackPressed();
        void onLogOutPressed();
    }

    interface Model {

    }

    interface View {
        void onSettingsPressed();
        void onBackPressed();
        void onLogOutPressed();
    }
}
