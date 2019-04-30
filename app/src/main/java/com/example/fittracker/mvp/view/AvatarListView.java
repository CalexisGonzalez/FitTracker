package com.example.fittracker.mvp.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fittracker.R;
import com.example.fittracker.adapter.MarvelAdapter;
import com.example.fittracker.mvp.contract.AvatarListContract;
import com.example.fittracker.services.marvel.Result;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvatarListView implements AvatarListContract.View {
    private WeakReference<Activity> activity;
    @BindView(R.id.avatarlist_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.avatarlist_progress_bar)
    ProgressBar progressBar;

    public AvatarListView(Activity activity) {
        this.activity = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
        init();
    }

    public void init() {
        recyclerView.setAdapter(new MarvelAdapter(new ArrayList<Result>()));
        LinearLayoutManager llm = new LinearLayoutManager(this.activity.get());
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public void onBackPressed() {
        activity.get().finish();
    }

    @Override
    public void setAdapter(MarvelAdapter adapter) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
    }

    @Override
    public void onImageError() {
        Toast.makeText(activity.get(), R.string.error_fetching_image, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryError() {
        Toast.makeText(activity.get(), R.string.error_fetching_api_data, Toast.LENGTH_SHORT).show();
    }
}
