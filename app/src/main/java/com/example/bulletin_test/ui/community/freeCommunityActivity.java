package com.example.bulletin_test.ui.community;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class freeCommunityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        findViewById(R.id.freePostBtn).setOnClickListener(onClickListener);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final ArrayList<PostInfo> latest_postList = new ArrayList<>();
        db.collection("bulletin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("로그: ", document.getId() + " => " + document.getData());
                                latest_postList.add(new PostInfo(
                                        document.getData().get("title").toString(),
                                        (ArrayList<String>) document.getData().get("content"),
                                        document.getData().get("publisher").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        (Integer) document.getData().get("recom")
                                ));
                            }
                            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.latest_Post);
                            recyclerView1.setHasFixedSize(true);
                            recyclerView1.setLayoutManager(new LinearLayoutManager(freeCommunityActivity.this));

                            RecyclerView.Adapter mAdapter1 = new communityAdapter(freeCommunityActivity.this, latest_postList);
                            recyclerView1.setAdapter(mAdapter1);
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
