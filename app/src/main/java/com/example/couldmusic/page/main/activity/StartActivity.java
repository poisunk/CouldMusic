package com.example.couldmusic.page.main.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.couldmusic.R;
import com.example.couldmusic.base.BaseActivity;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Thread(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.startMainActivity(this);
            finish();
        }).start();
    }
}
