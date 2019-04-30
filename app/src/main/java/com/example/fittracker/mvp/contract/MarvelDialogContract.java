package com.example.fittracker.mvp.contract;

import android.widget.ImageView;

public interface MarvelDialogContract {
    interface Presenter {
        public void onApplyPressed();

        public void onCancelPressed();
    }

    interface Model {
        public String getImageUrl();

        public int getUserId();

        public void updateImageUrl(int userId, String imageUrl);
    }

    interface View {
        public void onApplyPressed();

        public void onCancelPressed();

        public ImageView getImageView();

        public void hideProgressBar();

        public void onImageError();
    }
}
