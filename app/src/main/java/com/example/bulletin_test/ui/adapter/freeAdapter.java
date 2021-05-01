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
import com.example.bulletin_test.ui.writingContent.FreePostInfo;
import com.example.bulletin_test.ui.writingContent.RecipePostInfo;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class freeAdapter extends RecyclerView.Adapter<freeAdapter.communityViewHolder> {
    private ArrayList<FreePostInfo> mDataset;
    private Activity activity;


    static class communityViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        communityViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public freeAdapter(Activity activity, ArrayList<FreePostInfo> communityDataset){
        mDataset = communityDataset;
        this.activity = activity;
    }


    @NotNull
    @Override
    public freeAdapter.communityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        CardView cardView =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_free_post, parent,false);
        final communityViewHolder communityViewHolder = new communityViewHolder(cardView);
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
        TextView title = cardView.findViewById(R.id.freeTitle);
        title.setText(mDataset.get(position).getTitle());

        TextView createdAt = cardView.findViewById(R.id.freeCreatedAt);
        createdAt.setText(new SimpleDateFormat("MM-dd hh:mm", Locale.KOREA).format(mDataset.get(position).getCreatedAt()));

        TextView freeContent = cardView.findViewById(R.id.freeContent);
        freeContent.setText(mDataset.get(position).getContent());

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);



    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

}
