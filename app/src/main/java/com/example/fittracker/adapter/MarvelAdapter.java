package com.example.fittracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fittracker.ConstantUtils;
import com.example.fittracker.R;
import com.example.fittracker.dialog.MarvelDialog;
import com.example.fittracker.services.marvel.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.PhotoViewHolder> {

    private List<Result> items;

    public MarvelAdapter(List<Result> items) {
        this.items = items;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_avatar,
                parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder viewHolder, int position) {
        final Result imageInfo = items.get(position);
        String imageUrl = imageInfo.getThumbnail().getPath() + "." + imageInfo.getThumbnail().getExtension();
        viewHolder.imageUrl = imageUrl;
        viewHolder.id.setText(String.valueOf(imageInfo.getName()));
        Picasso.get().load(imageUrl).resize(ConstantUtils.MARVEL_IMAGE_SMALL_DIMENSION,
                ConstantUtils.MARVEL_IMAGE_SMALL_DIMENSION).into(viewHolder.image, new Callback() {
            @Override
            public void onSuccess() {
                if (viewHolder.progressBar != null) {
                    viewHolder.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar_recyclerview_image) ImageView image;
        @BindView(R.id.avatar_recyclerview_textview) TextView id;
        @BindView(R.id.avatar_recyclerview_progressBar) ProgressBar progressBar;
        String imageUrl;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.avatar_recyclerview_image)
        public void onImageClick(View view) {
            new MarvelDialog(view.getContext(), imageUrl).show();
        }
    }
}
