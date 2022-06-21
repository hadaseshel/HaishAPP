package com.example.haioshapp;

import android.app.Application;
import android.content.Context;

public class MyAPP extends Application {
    public static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }
}
