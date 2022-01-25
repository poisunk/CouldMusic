package com.example.couldmusic.page.activity;


import android.os.Bundle;

import com.example.couldmusic.R;
import com.example.couldmusic.base.BaseActivity;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                    MainActivity.startMainActivity(StartActivity.this);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();

                }
            }
        }).start();
    }
}