package com.example.fittracker.mvp.view;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.MarvelDialogContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarvelDialogView implements MarvelDialogContract.View {
    private WeakReference<Dialog> dialog;
    @BindView(R.id.dialog_avatar_image)
    ImageView image;
    @BindView(R.id.dialog_avatar_progressBar)
    ProgressBar progressBar;

    public MarvelDialogView(Dialog dialog) {
        this.dialog = new WeakReference<>(dialog);
        ButterKnife.bind(this, dialog);
        this.dialog.get().setTitle(null);
        this.dialog.get().setCancelable(false);
    }

    @Override
    public void onApplyPressed() {
        Toast.makeText(dialog.get().getContext(), R.string.msg_on_changes_applied, Toast.LENGTH_SHORT).show();
        dialog.get().dismiss();
    }

    @Override
    public void onCancelPressed() {
        dialog.get().dismiss();
    }

    @Override
    public ImageView getImageView() {
        return image;
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onImageError() {
        Toast.makeText(dialog.get().getContext(), R.string.error_fetching_image, Toast.LENGTH_SHORT).show();
    }
}
