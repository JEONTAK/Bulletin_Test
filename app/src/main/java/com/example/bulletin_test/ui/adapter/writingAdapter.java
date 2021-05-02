/*
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

import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.writingContent.RecipePostInfo;
import com.example.bulletin_test.ui.writingContent.writingRecipePostActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class writingAdapter extends RecyclerView.Adapter<writingAdapter.writingViewHolder> {
    private ArrayList<RecipePostInfo> mDataset;
    private Activity activity;

    public writingAdapter(writingRecipePostActivity activity, LinearLayout linearLayout) {
    }


    static class writingViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        writingViewHolder(Activity activity,CardView v, RecipePostInfo recipePostInfo){
            super(v);
            cardView = v;

            LinearLayout recipeContentLayout = cardView.findViewById(R.id.recipeContentLayout);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ArrayList<String> recipeContentList = recipePostInfo.getContent();

            if(recipeContentLayout.getChildCount()==0){
                for(int i = 0 ; i < recipeContentList.size() ; i++){
                    String recipeContent = recipeContentList.get(i);
                    if(Patterns.WEB_URL.matcher(recipeContent).matches()){
                        ImageView imageView = new ImageView(activity);
                        imageView.setLayoutParams(layoutParams);
                        //imageView.setAdjustViewBounds(true);
                        //imageView.setScaleType(ImageView.ScaleType.FIT_XY); //이미지 꽉차게함
                        recipeContentLayout.addView(imageView);
                        //Glide.with(activity).load(recipeContent).override(1000).thumbnail(0.1f).into(imageView);
                    }else{
                        TextView textView = new TextView(activity);
                        textView.setLayoutParams(layoutParams);
                        //textView.setText(recipeContent);
                        recipeContentLayout.addView(textView);
                    }
                }
            }
        }
    }

    public writingAdapter(Activity activity, ArrayList<RecipePostInfo> writingDataset){
        mDataset = writingDataset;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @NotNull
    @Override
    public writingAdapter.writingViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        CardView cardView =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_post, parent,false);
        final writingViewHolder writingViewHolder = new writingViewHolder(activity, cardView, mDataset.get(viewType));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return writingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull final writingViewHolder holder, int position){
        CardView cardView = holder.cardView;
        TextView title = cardView.findViewById(R.id.recipeTitle);
        title.setText(mDataset.get(position).getTitle());

        TextView createdAt = cardView.findViewById(R.id.recipeCreatedAt);
        createdAt.setText(new SimpleDateFormat("MM-dd-hh:mm", Locale.getDefault()).format(mDataset.get(position).getCreatedAt()));

        LinearLayout recipeContentLayout = cardView.findViewById(R.id.recipeContentLayout);
        ArrayList<String> recipeContentList = mDataset.get(position).getContent();

        for(int i = 0 ; i < recipeContentList.size() ; i++){
            String recipeContent = recipeContentList.get(i);
            if(Patterns.WEB_URL.matcher(recipeContent).matches()){
                Glide.with(activity).load(recipeContent).override(1000).thumbnail(0.1f).into((ImageView)recipeContentLayout.getChildAt(i));
            }else{
                ((TextView)recipeContentLayout.getChildAt(i)).setText(recipeContent);
            }
        }

    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

}
*/
