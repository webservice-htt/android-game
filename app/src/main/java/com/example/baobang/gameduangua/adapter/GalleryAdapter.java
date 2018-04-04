package com.example.baobang.gameduangua.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.model.Gallery;

import java.util.List;

/*
 * Created by baobang on 4/4/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {

    private Context mContext;
    private List<Gallery> mGalleries;

    public GalleryAdapter(Context mContext, List<Gallery> mGalleries) {
        this.mContext = mContext;
        this.mGalleries = mGalleries;
    }

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view  = layoutInflater.inflate(R.layout.gallery_card_view_item, parent, false);
        return new GalleryHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {
        holder.onBindView(mGalleries.get(position));
    }

    @Override
    public int getItemCount() {
        return mGalleries.size();
    }

    class GalleryHolder extends RecyclerView.ViewHolder {


        ImageView  imgGallery;
        TextView txtGalleryName;
        TextView txtDescription;


        GalleryHolder(View itemView) {
            super(itemView);
            imgGallery = itemView.findViewById(R.id.imgGallery);
            txtGalleryName = itemView.findViewById(R.id.txtGalleryName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }

        void onBindView(Gallery gallery) {

            txtGalleryName.setText(gallery.getName());
            txtDescription.setText(gallery.getDescription());
            Glide.with(mContext)
                    .load(gallery.getPhotUrl())
                    .into(imgGallery);
        }
    }
}
