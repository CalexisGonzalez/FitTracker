package com.example.fittracker.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.fittracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PresentationActivity extends AppCompatActivity {
    @BindView(R.id.presentation_activity_logo)
    ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        ButterKnife.bind(this);
        final Intent intent = new Intent(this, MainActivity.class);
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.
                loadAnimator(getApplicationContext(), R.animator.animation_logo);
        animator.setTarget(logo);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(intent);
            }
        });
        animator.start();
    }
}