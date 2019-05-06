package com.example.fittracker.mvp.contract;

import android.widget.ImageView;

public interface ExerciseDialogContract {
    interface Presenter {

    }

    interface Model {
        String getImageUrl(int pos);
    }

    interface View {
        void onImageError();

        ImageView getImageView(int pos);

        void hideProgressBar(int pos);
    }
}
