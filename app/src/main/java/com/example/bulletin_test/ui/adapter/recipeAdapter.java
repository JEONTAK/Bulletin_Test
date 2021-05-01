package com.example.bulletin_test.ui.adapter;

import android.app.Activity;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.writingContent.RecipePostInfo;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.communityViewHolder> {
    private ArrayList<RecipePostInfo> mDataset;
    private Activity activity;


    static class communityViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        communityViewHolder(Activity activity,CardView v, RecipePostInfo recipePostInfo){
            super(v);
            cardView = v;
        }
    }

    public recipeAdapter(Activity activity, ArrayList<RecipePostInfo> communityDataset){
        mDataset = communityDataset;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @NotNull
    @Override
    public recipeAdapter.communityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        CardView cardView =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_post, parent,false);
        final communityViewHolder communityViewHolder = new communityViewHolder(activity, cardView, mDataset.get(viewType));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return communityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull final communityViewHolder holder, int position){
        CardView cardView = holder.cardView;
        ImageView titleImage = cardView.findViewById(R.id.recipeTitleImage);
        String titleImagePath = mDataset.get(position).getTitleImage();
        if(Patterns.WEB_URL.matcher(titleImagePath).matches()){
            Glide.with(activity).load(titleImagePath).override(1000).thumbnail(0.1f).into(titleImage);
        }
        TextView title = cardView.findViewById(R.id.recipeTitle);
        title.setText(mDataset.get(position).getTitle());

        TextView publisher = cardView.findViewById(R.id.recipePublisher);
        publisher.setText(mDataset.get(position).getPublisher());

        TextView createdAt = cardView.findViewById(R.id.recipeCreatedAt);
        createdAt.setText(new SimpleDateFormat("MM-dd-hh:mm", Locale.getDefault()).format(mDataset.get(position).getCreatedAt()));

        TextView recom = cardView.findViewById(R.id.recipeRecom);
        publisher.setText("추천수 : " + (int) mDataset.get(position).getRecom());

    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

}
