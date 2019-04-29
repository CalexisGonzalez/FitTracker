package com.example.fittracker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.MarvelDialogContract;
import com.example.fittracker.mvp.model.MarvelDialogModel;
import com.example.fittracker.mvp.presenter.MarvelDialogPresenter;
import com.example.fittracker.mvp.view.MarvelDialogView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarvelDialog extends Dialog {
    private String imageUrl;
    private MarvelDialogContract.Presenter presenter;

    public MarvelDialog(Context context, String imageUrl) {
        super(context);
        this.imageUrl = imageUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_avatar_image);
        ButterKnife.bind(this);
        presenter = new MarvelDialogPresenter(new MarvelDialogModel(imageUrl), new MarvelDialogView(this));
    }

    @OnClick(R.id.dialog_avatar_button_apply)
    public void onApplyPressed(){ presenter.onApplyPressed();}

    @OnClick(R.id.dialog_avatar_button_cancel)
    public void onCancelPressed(){ presenter.onCancelPressed();}
}
