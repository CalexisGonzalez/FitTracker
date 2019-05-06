package com.example.fittracker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.ExerciseDialogContract;
import com.example.fittracker.mvp.model.ExerciseDialogModel;
import com.example.fittracker.mvp.presenter.ExerciseDialogPresenter;
import com.example.fittracker.mvp.view.ExerciseDialogView;
import com.example.fittracker.services.workout.WorkoutUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDialog extends Dialog {
    private WorkoutUnit unit;
    private ExerciseDialogContract.Presenter presenter;
    @BindView(R.id.dialog_exercise_image_left)
    ImageView leftImage;
    @BindView(R.id.dialog_exercise_image_right) ImageView rightImage;
    @BindView(R.id.dialog_exercise_progressBar_left)
    ProgressBar progressBarLeft;
    @BindView(R.id.dialog_exercise_progressBar_right)
    ProgressBar progressBarRight;

    public ExerciseDialog(Context context, WorkoutUnit unit) {
        super(context);
        this.unit = unit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exercise_image);
        ButterKnife.bind(this);
        presenter = new ExerciseDialogPresenter(new ExerciseDialogModel(unit), new ExerciseDialogView(this));
    }
}
