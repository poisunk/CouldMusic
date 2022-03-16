package com.example.couldmusic.page.main.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.base.BaseActivity;
import com.example.couldmusic.page.main.fragment.MainFragment;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class MainActivity extends BaseActivity {

    private final FragmentManager fragmentManager=getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new MainFragment(),"MainFragment");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限不够获取不到音乐，程序将退出", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            // 回退栈中至少有多个fragment,栈底部是首页
            if (backStackEntryCount > 1) {
                // 立即回退一步
                fragmentManager.popBackStackImmediate();
            } else {
                //回退栈中只剩一个时,退出应用
                finish();
            }
        }
        return true;
    }

    private void addFragment(Fragment fragment,String tag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.included_interface, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public static void startMainActivity(Context context) {
        Intent intent =new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
