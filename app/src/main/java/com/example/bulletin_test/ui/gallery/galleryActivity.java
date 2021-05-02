package com.example.bulletin_test.ui.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.adapter.galleryAdapter;
import com.example.bulletin_test.ui.login.MemberInfo;
import com.example.bulletin_test.ui.writingContent.writingRecipePostActivity;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import static android.provider.MediaStore.MediaColumns.DATE_TAKEN;
import static com.example.bulletin_test.Util.showToast;

public class galleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if(ContextCompat.checkSelfPermission(galleryActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(galleryActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
            if(ActivityCompat.shouldShowRequestPermissionRationale(galleryActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
            }else{
                showToast(galleryActivity.this ,"권한을 혀용해 주세요");
            }
        }else{
            recyclerInit();
        }
    }

    private void recyclerInit(){
        final int numberOfColumns = 3;
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));

        RecyclerView.Adapter mAdapter = new galleryAdapter(this,getImagePath(this));
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @Nonnull String permissions[], @Nonnull int[] grantResult){
        switch(requestCode){
            case 1:{
                if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                   recyclerInit();
                }else{
                    finish();
                    showToast(galleryActivity.this ,"권한을 허용해주세요");
                }
            }
        }
    }

    public ArrayList<String> getImagePath(Activity activity){
        Uri uri;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        Cursor cursor;
        int column_index_data;
        String PathOfImage = null;
        String[] projection;
        Intent intent = getIntent();

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        projection = new String[] {MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri,projection,null,null, "\'" + MediaStore.Images.ImageColumns.DATE_TAKEN + "\' ASC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()){
            PathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(PathOfImage);
        }
        return listOfAllImages;
    }

}
