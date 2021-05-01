package com.example.bulletin_test.ui.community;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.writingContent.RecipePostInfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.bulletin_test.Util.isStorageUrl;
import static com.example.bulletin_test.Util.showToast;

public class recipeInformationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        RecipePostInfo recipePostInfo = (RecipePostInfo) getIntent().getSerializableExtra("recipePostInfo");

        ImageView recipeInfoTitleImage = findViewById(R.id.titleImage);

        String infoTitleImagePath = recipePostInfo.getTitleImage();
        if(isStorageUrl(infoTitleImagePath)){
            Glide.with(this).load(infoTitleImagePath).override(1000).thumbnail(0.1f).into(recipeInfoTitleImage);
        }
        Log.d("로그","" + recipePostInfo.getTitleImage());

        TextView recipeInfoTitle = findViewById(R.id.recipeInfoTitle);
        recipeInfoTitle.setText(recipePostInfo.getTitle());
        Log.d("로그","" + recipePostInfo.getTitle());

        TextView recipeRecomNum = findViewById(R.id.recomNumber);
        recipeRecomNum.setText(Integer.toString((int) recipePostInfo.getRecom()));
        Log.d("로그","" + recipePostInfo.getRecom());

        TextView recipeCreatedAt = findViewById(R.id.recipeInfoCreatedAt);
        recipeCreatedAt.setText(new SimpleDateFormat("MM-dd hh:mm", Locale.KOREA).format(recipePostInfo.getCreatedAt()));
        Log.d("로그","" + recipePostInfo.getCreatedAt());

        LinearLayout recipeContentLayout = findViewById(R.id.recipeContentLayout);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ArrayList<String> recipeContentList = recipePostInfo.getContent();
        Log.d("로그","" + recipePostInfo.getContent());
        if(recipeContentLayout.getChildCount()==0){

            for(int i = 1 ; i < recipeContentList.size() ; i++){
                Log.d("로그","" + recipeContentList.get(i));
                String recipeContent = recipeContentList.get(i);
                if(Patterns.WEB_URL.matcher(recipeContent).matches() && recipeContent.contains("https://firebasestorage.googleapis.com/v0/b/bulletin-test-a74a5.appspot.com/o/recipePost")){
                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(layoutParams);
                    //imageView.setAdjustViewBounds(true);
                    //imageView.setScaleType(ImageView.ScaleType.FIT_XY); //이미지 꽉차게함
                    recipeContentLayout.addView(imageView);
                    //Glide.with(activity).load(recipeContent).override(1000).thumbnail(0.1f).into(imageView);
                }else{
                    TextView textView = new TextView(this);
                    textView.setLayoutParams(layoutParams);
                    //textView.setText(recipeContent);
                    recipeContentLayout.addView(textView);
                }
            }
        }

    }
}
