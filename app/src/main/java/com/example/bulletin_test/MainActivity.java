package com.example.bulletin_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bulletin_test.ui.community.communityActivity;
import com.example.bulletin_test.ui.login.MemberActivity;
import com.example.bulletin_test.ui.login.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    private static final String TAG= "MemberActivity";//태그 설정 (오류 확인용)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //파이어베이스 유저 선언

        if (user == null) {//만약 로그인이 안되어 있다면 회원가입창 띄우기
            myStartActivity(SignUpActivity.class);
        }else {//로그인이 됐다면
            myStartActivity(communityActivity.class);

            FirebaseFirestore db = FirebaseFirestore.getInstance();//데이터베이스 선언
            DocumentReference docRef = db.collection("users").document(user.getUid());//유저의 개인 아이디를 통해 데이터를  읽어옴
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document!=null){
                            if (document.exists()) {//회원정보가 있다면 회원정보 읽어옴
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberActivity.class);//회원정보가 없다면 회원정보 화면으로 전환
                            }
                        }
                    } else {//데이터 읽기 실패
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
        findViewById(R.id.goCommunityBtn).setOnClickListener(onClickListener);
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {//로그아웃 버튼 누르면 회원가입창 띄움
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    myStartActivity(SignUpActivity.class);
                    break;
                case R.id.goCommunityBtn:
                    myStartActivity(communityActivity.class);
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