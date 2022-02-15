package com.example.couldmusic.main.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.base.BaseActivity;
import com.example.couldmusic.list.view.ListFragment;
import com.example.couldmusic.main.fragment.MainFragment;
import com.example.couldmusic.music.MusicFragment;
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

        /**
         * 主页（MainFragment）与播放音乐界面（MusicFragment）一直被加入到主活动当中
         * 当需要时直接加载
         */
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.add(R.id.included_interface,MainFragment.getInstance());
        transaction.add(R.id.included_interface,MusicFragment.getInstance());
        transaction.show(MainFragment.getInstance()).hide(MusicFragment.getInstance());
        transaction.commit();
    }


    /**
     * 获取权限
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @SuppressLint("MissingSuperCall")
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[]permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限不够获取不到音乐，程序将退出", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

}