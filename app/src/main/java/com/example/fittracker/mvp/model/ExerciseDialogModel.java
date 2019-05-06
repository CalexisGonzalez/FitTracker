package com.example.fittracker.mvp.model;

import com.example.fittracker.mvp.contract.ExerciseDialogContract;
import com.example.fittracker.services.workout.WorkoutUnit;

public class ExerciseDialogModel implements ExerciseDialogContract.Model {
    private WorkoutUnit unit;

    public ExerciseDialogModel(WorkoutUnit unit) {
        this.unit = unit;
    }

    @Override
    public String getImageUrl(int pos) {
        return unit.images().get(pos).getImage();
    }
}
