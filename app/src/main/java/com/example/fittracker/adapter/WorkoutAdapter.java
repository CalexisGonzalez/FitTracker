package com.example.fittracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fittracker.BusProvider;
import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.dialog.ExerciseDialog;
import com.example.fittracker.services.workout.WorkoutUnit;
import com.squareup.otto.Bus;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ExerciseViewHolder> {

    private List<WorkoutUnit> units;
    private Bus bus;

    public WorkoutAdapter(List<WorkoutUnit> units) {
        this.units = units;
        bus = BusProvider.getInstance();
    }

    @Override
    public WorkoutAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_exercise,
                parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WorkoutAdapter.ExerciseViewHolder viewHolder, int position) {
        final WorkoutUnit unit = units.get(position);
        viewHolder.description.setText(unit.getExercise());
        viewHolder.unit = unit;
        Picasso.get().load(unit.images().get(ConstantUtils.ZERO).getImage())
                .resize(ConstantUtils.EXERCISE_IMAGE_SMALL_DIMENSION, ConstantUtils.EXERCISE_IMAGE_SMALL_DIMENSION)
                .into(viewHolder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (viewHolder.progressBar != null) {
                            viewHolder.progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        bus.post(e);
                    }
                });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return units != null ? units.size() : ConstantUtils.ZERO;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.exercise_recyclerview_image) ImageView image;
        @BindView(R.id.exercise_recyclerview_textview) TextView description;
        @BindView(R.id.exercise_recyclerview_progressBar) ProgressBar progressBar;
        WorkoutUnit unit;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.exercise_recyclerview_image)
        public void onImageClick(View view) {
            new ExerciseDialog(view.getContext(),unit).show();
        }
    }
}
