package com.example.fittracker.mvp.view;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.ExerciseDialogContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDialogView implements ExerciseDialogContract.View {
    @BindView(R.id.dialog_exercise_image_left)
    ImageView leftImage;
    @BindView(R.id.dialog_exercise_image_right) ImageView rightImage;
    @BindView(R.id.dialog_exercise_progressBar_left)
    ProgressBar progressBarLeft;
    @BindView(R.id.dialog_exercise_progressBar_right)
    ProgressBar progressBarRight;
    private WeakReference<Dialog> dialog;

    public ExerciseDialogView(Dialog dialog) {
        this.dialog = new WeakReference<>(dialog);
        ButterKnife.bind(this, dialog);
        this.dialog.get().setTitle(null);
    }

    @Override
    public void onImageError() {
        Toast.makeText(dialog.get().getContext(), R.string.error_fetching_image, Toast.LENGTH_SHORT).show();
    }

    @Override
    public ImageView getImageView(int pos) {
        switch (pos) {
            case ConstantUtils.ZERO:
                return leftImage;
            case ConstantUtils.ONE:
                return rightImage;
        }
        return null;
    }

    @Override
    public void hideProgressBar(int pos) {
        switch (pos) {
            case ConstantUtils.ZERO:
                progressBarLeft.setVisibility(View.GONE);
                break;
            case ConstantUtils.ONE:
                progressBarRight.setVisibility(View.GONE);
                break;
        }
    }
}
