package com.example.bulletin_test;

import android.app.Activity;
import android.util.Patterns;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Util {

    public Util(){
    }

    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
    }

    public static boolean isStorageUrl(String url){
        return Patterns.WEB_URL.matcher(url).matches();
    }
}
