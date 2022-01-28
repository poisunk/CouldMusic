package com.example.couldmusic.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class App extends Application {


    @SuppressLint("StaticFieldLeak")
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
    }
}
