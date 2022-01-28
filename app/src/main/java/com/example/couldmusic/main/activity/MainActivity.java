package com.example.couldmusic.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.base.BaseActivity;
import com.example.couldmusic.main.fragment.MainFragment;
import com.example.couldmusic.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    public static void startMainActivity(Context context){
        Intent intent=new Intent(context , MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment= new MainFragment();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.included_interface,fragment);
        transaction.commit();
    }

}