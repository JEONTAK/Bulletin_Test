package com.example.bulletin_test.ui.community;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.adapter.communityAdapter;
import com.example.bulletin_test.ui.writingContent.PostInfo;
import com.example.bulletin_test.ui.writingContent.writingPostActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class freeCommunityActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView freeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_bulletin);
        findViewById(R.id.freePostBtn).setOnClickListener(onClickListener);
        firebaseFirestore= FirebaseFirestore.getInstance();//데이터베이스 선언

        freeRecyclerView = findViewById(R.id.post1);
        freeRecyclerView.setHasFixedSize(true);
        freeRecyclerView.setLayoutManager(new LinearLayoutManager(freeCommunityActivity.this));

        CollectionReference collectionReference = firebaseFirestore.collection("bulletin");
        collectionReference
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<PostInfo> free_postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("로그: ", document.getId() + " => " + document.getData());
                                free_postList.add(new PostInfo(
                                        document.getData().get("title").toString(),
                                        (ArrayList<String>) document.getData().get("content"),
                                        document.getData().get("publisher").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        (Long) document.getData().get("recom")
                                ));
                            }

                            RecyclerView.Adapter mAdapter1 = new communityAdapter(freeCommunityActivity.this, free_postList);
                            freeRecyclerView.setAdapter(mAdapter1);
                        } else {
                            Log.d("로그: ", "Error getting documents: ", task.getException());

                        }
                    }
                });


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.freePostBtn:
                    myStartActivity(writingPostActivity.class);
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