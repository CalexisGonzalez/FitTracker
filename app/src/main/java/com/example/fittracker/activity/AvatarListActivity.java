package com.example.fittracker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.fittracker.BusProvider;
import com.example.fittracker.R;
import com.example.fittracker.mvp.contract.AvatarListContract;
import com.example.fittracker.mvp.model.AvatarListModel;
import com.example.fittracker.mvp.presenter.AvatarListPresenter;
import com.example.fittracker.mvp.view.AvatarListView;
import com.example.fittracker.services.marvel.MarvelGenerator;
import com.example.fittracker.services.marvel.MarvelService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AvatarListActivity extends AppCompatActivity {
    private AvatarListContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_list);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        presenter = new AvatarListPresenter(new AvatarListModel
                (MarvelGenerator.createService(MarvelService.class)),
                new AvatarListView(this));
    }

    @OnClick(R.id.avatarlist_button_back)
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }
}
