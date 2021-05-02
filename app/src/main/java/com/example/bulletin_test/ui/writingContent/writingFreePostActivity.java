package com.example.bulletin_test.ui.writingContent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.community.communityActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;

import static com.example.bulletin_test.Util.showToast;

public class writingFreePostActivity extends AppCompatActivity {

    private static final String TAG =" freePostActivity";
    private FirebaseUser user;
    private RelativeLayout loaderLayout;
    private LinearLayout parent;
    private EditText selectedEditText;

    private int pathCount , successCount;
    //dbUploader

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_post);

        loaderLayout = findViewById(R.id.loaderLayout);
        
        findViewById(R.id.confirmBtn).setOnClickListener(onClickListener);
        findViewById(R.id.goBackBtn).setOnClickListener(onClickListener);
        findViewById(R.id.editIngredient_Recipe).setOnFocusChangeListener(onFocusChangeListener);
        findViewById(R.id.editTitle_Recipe).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    selectedEditText = null;
                }
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.goBackBtn:
                    myStartActivity(communityActivity.class);
                    break;
                case R.id.confirmBtn:
                    bulletinUpload();
                    break;
            }
        }

    };

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener(){
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                selectedEditText = (EditText) v;
            }
        }
    };


    private void bulletinUpload(){
        final String title = ((EditText)findViewById(R.id.editTitle_Recipe)).getText().toString();
        final String contentsList = ((EditText)findViewById(R.id.editIngredient_Recipe)).getText().toString();

        if(title.length() > 0 && contentsList.length()> 0){
            loaderLayout.setVisibility(View.VISIBLE);
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = firebaseFirestore.collection("freePost").document();
            FreePostInfo freePostInfo = new FreePostInfo(title, contentsList, user.getUid(), new Date());
            dbUploader(documentReference, freePostInfo);
        }
        else{
            showToast(writingFreePostActivity.this ,"내용을 정확히 입력해주세요!");
        }
    }

    private void dbUploader(DocumentReference documentReference , FreePostInfo freePostInfo){
        documentReference.set(freePostInfo)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    loaderLayout.setVisibility(View.GONE);
                    showToast(writingFreePostActivity.this ,"게시글 등록 성공!");
                    Log.w(TAG,"Success writing document" + documentReference.getId());
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            loaderLayout.setVisibility(View.GONE);
            showToast(writingFreePostActivity.this ,"게시글 등록 실패.");
            Log.w(TAG,"Error writing document", e);
        }
        });
    }


    private void myStartActivity(Class c){
        Intent intent=new Intent( this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}

