package com.example.fittracker.services.workout;

import com.example.fittracker.ConstantUtils;

import java.net.URI;
import java.util.List;

public class WorkoutUnit {
    private String exercise;
    private List<Result> images;

    public WorkoutUnit(List<Result> images) {
        this.images = images;
        setExercise();
    }

    public String getExercise() {
        return exercise;
    }

    public List<Result> images() {
        return images;
    }

    private void setExercise() {
        String imageUrl = images.get(ConstantUtils.ONE).getImage();
        exercise = imageUrl.substring(imageUrl.lastIndexOf(ConstantUtils.SLASH)+ConstantUtils.ONE,
                imageUrl.length()-ConstantUtils.WORKOUT_IMAGEDESCRIPTION_OFFSET)
                .replace(ConstantUtils.HYPHEN,ConstantUtils.SPACE);
    }
}
