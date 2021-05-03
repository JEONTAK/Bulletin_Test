package com.example.bulletin_test.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bulletin_test.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG= "MemberActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);


    }

    @Override
    public void onBackPressed() {//뒤로가기 누르면 프로그램 종료
        super.onBackPressed();
        finish();
    }


    View.OnClickListener onClickListener=new View.OnClickListener() {//확인 버튼 누르면 회원정보 등록
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkButton:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate() {//회원정보 등록하는 함수
        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.PhoneEditText)).getText().toString();
        String Date = ((EditText) findViewById(R.id.DateEditText)).getText().toString();
        String adress = ((EditText) findViewById(R.id.PostalAddressEditText)).getText().toString();


        if (name.length() > 0 &&phoneNumber.length()>9&&Date.length()>5&&adress.length()>0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();//회원정보 데이터베이스 선언
            // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            com.example.bulletin_test.ui.login.MemberInfo memberInfo = new com.example.bulletin_test.ui.login.MemberInfo(name,phoneNumber,Date,adress, user.getUid());//Memberinfo 이름=new 이름(속성);


            if(user!=null){//만약 사용자가 없다면
                db.collection("users").document(user.getUid()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("등록에 성공하였습니다");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("등록에 실패하였습니다");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });


            }
        }else{
            startToast("회원정보를 입력해주세요");
        }
    }
    private void startToast(String msg){//토스팅 하는 함수
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//토스트 메세지 띄우는 함수

    }


