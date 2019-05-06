package com.example.fittracker.mvp.presenter;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.mvp.contract.ExerciseDialogContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ExerciseDialogPresenter implements ExerciseDialogContract.Presenter {
    private ExerciseDialogContract.Model model;
    private ExerciseDialogContract.View view;

    public ExerciseDialogPresenter(ExerciseDialogContract.Model model, ExerciseDialogContract.View view) {
        this.model = model;
        this.view = view;
        init();
    }

    public void init() {
        Picasso.get().load(model.getImageUrl(ConstantUtils.ZERO)).resize(ConstantUtils.EXERCISE_IMAGE_LARGE_DIMENSION,
                ConstantUtils.EXERCISE_IMAGE_LARGE_DIMENSION).into(view.getImageView(ConstantUtils.ZERO), new Callback() {
            @Override
            public void onSuccess() {
                view.hideProgressBar(ConstantUtils.ZERO);
            }

            @Override
            public void onError(Exception e) {
                view.onImageError();
            }
        });
        Picasso.get().load(model.getImageUrl(ConstantUtils.ONE)).resize(ConstantUtils.EXERCISE_IMAGE_LARGE_DIMENSION,
                ConstantUtils.EXERCISE_IMAGE_LARGE_DIMENSION).into(view.getImageView(ConstantUtils.ONE), new Callback() {
            @Override
            public void onSuccess() {
                view.hideProgressBar(ConstantUtils.ONE);
            }

            @Override
            public void onError(Exception e) {
                view.onImageError();
            }
        });
    }
}
