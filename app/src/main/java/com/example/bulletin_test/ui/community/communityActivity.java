package com.example.bulletin_test.ui.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.adapter.recipeAdapter;

import com.example.bulletin_test.ui.writingContent.RecipePostInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;


public class communityActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    final int numberOfColumns = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        firebaseFirestore= FirebaseFirestore.getInstance();//데이터베이스 선언

        findViewById(R.id.recipe_Bulletin).setOnClickListener(onClickListener);
        findViewById(R.id.free_Bulletin).setOnClickListener(onClickListener);

        recyclerView1 = (RecyclerView)findViewById(R.id.latest_Post);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new GridLayoutManager(communityActivity.this,numberOfColumns));


        recyclerView2 = (RecyclerView)findViewById(R.id.hot_Post);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(communityActivity.this,numberOfColumns));

        CollectionReference collectionReference = firebaseFirestore.collection("recipePost");
        collectionReference
                .orderBy("recom", Query.Direction.DESCENDING).limit(6)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            ArrayList<RecipePostInfo> latest_postList = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d("로그: ", document.getId() + " => " + document.getData());
                                latest_postList.add(new RecipePostInfo(
                                        document.getData().get("titleImage").toString(),
                                        document.getData().get("title").toString(),
                                        (ArrayList<String>) document.getData().get("content"),
                                        document.getData().get("publisher").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        (Long) document.getData().get("recom")
                                ));
                            }

                            RecyclerView.Adapter mAdapter1 = new recipeAdapter(communityActivity.this, latest_postList);
                            recyclerView1.setAdapter(mAdapter1);
                        }
                        else{
                            Log.d("로그: ", "Error getting documents: " , task.getException());

                        }
                    }
                });


        collectionReference
                .orderBy("createdAt", Query.Direction.DESCENDING).limit(6)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            ArrayList<RecipePostInfo> hot_postList = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d("로그: ", document.getId() + " => " + document.getData());
                                hot_postList.add(new RecipePostInfo(
                                        document.getData().get("titleImage").toString(),
                                        document.getData().get("title").toString(),
                                        (ArrayList<String>) document.getData().get("content"),
                                        document.getData().get("publisher").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        (Long) document.getData().get("recom")
                                ));
                            }
                            RecyclerView.Adapter mAdapter2 = new recipeAdapter(communityActivity.this, hot_postList);
                            recyclerView2.setAdapter(mAdapter2);
                        }
                        else{
                            Log.d("로그: ", "Error getting documents: " , task.getException());

                        }
                    }
                });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.recipe_Bulletin:
                    myStartActivity(recipeCommunityActivity.class);
                    break;
                case R.id.free_Bulletin:
                    myStartActivity(freeCommunityActivity.class);
                    break;
            }
        }
    };

    private void myStartActivity(Class c){
        Intent intent=new Intent( this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}