package com.example.couldmusic.util;

import android.widget.Toast;

import com.example.couldmusic.base.App;

public class ToastUtil {
    public static void show(String s) {
        Toast.makeText(App.appContext, s, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String s) {
        Toast.makeText(App.appContext, s, Toast.LENGTH_LONG).show();
    }
}
