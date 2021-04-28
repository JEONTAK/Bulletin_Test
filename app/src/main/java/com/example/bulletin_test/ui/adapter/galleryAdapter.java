package com.example.bulletin_test.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.gallery.galleryActivity;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class galleryAdapter extends RecyclerView.Adapter<galleryAdapter.galleryViewHolder> {
    private ArrayList<String> mDataset;
    private Activity activity;


    static class galleryViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        galleryViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public galleryAdapter(Activity activity, ArrayList<String> galleryDataset){
        mDataset = galleryDataset;
        this.activity = activity;
    }

    @NotNull
    @Override
    public galleryAdapter.galleryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        CardView cardView =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent,false);
        return new galleryViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NotNull final galleryViewHolder holder, int position){

        CardView cardView = holder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("profilePath",mDataset.get(holder.getAdapterPosition()));
                activity.setResult(Activity.RESULT_OK,resultIntent);
                activity.finish();
            }
        });

        ImageView imageView = cardView.findViewById(R.id.imageView);
        Glide.with(activity).load(mDataset.get(position)).centerCrop().override(500).into(imageView);

    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

}
