package com.example.fittracker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarvelDialog extends Dialog {
    private String imageUrl;
    @BindView(R.id.dialog_avatar_image)
    ImageView image;
    @BindView(R.id.dialog_avatar_progressBar)
    ProgressBar progressBar;

    public MarvelDialog(Context context, String imageUrl) {
        super(context);
        this.imageUrl = imageUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_avatar_image);
        ButterKnife.bind(this);
        setTitle(null);
        setCancelable(false);
        Picasso.get().load(imageUrl).resize(ConstantUtils.MARVEL_IMAGE_LARGE_DIMENSION,
                ConstantUtils.MARVEL_IMAGE_LARGE_DIMENSION).into(image, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @OnClick(R.id.dialog_avatar_button_cancel)
    public void onCancelClick() {
        dismiss();
    }

    @OnClick(R.id.dialog_avatar_button_apply)
    public void onApplyClick(){}
}
