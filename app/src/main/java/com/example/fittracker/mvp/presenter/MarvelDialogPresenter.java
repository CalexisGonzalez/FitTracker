package com.example.fittracker.mvp.presenter;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.mvp.contract.MarvelDialogContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MarvelDialogPresenter implements MarvelDialogContract.Presenter {
    private MarvelDialogContract.View view;
    private MarvelDialogContract.Model model;
    private String imageUrl;

    public MarvelDialogPresenter(MarvelDialogContract.Model model, MarvelDialogContract.View view) {
        this.model = model;
        this.view = view;
        init();
    }

    @Override
    public void onApplyPressed() {
        view.onApplyPressed();
    }

    @Override
    public void onCancelPressed() {
        view.onCancelPressed();
    }

    public void init() {
        imageUrl = model.getImageUrl();
        Picasso.get().load(imageUrl).resize(ConstantUtils.MARVEL_IMAGE_LARGE_DIMENSION,
                ConstantUtils.MARVEL_IMAGE_LARGE_DIMENSION).into(view.getImageView(), new Callback() {
            @Override
            public void onSuccess() {
                view.hideProgressBar();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
