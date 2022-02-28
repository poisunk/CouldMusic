package com.example.couldmusic.page.login.model;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.page.login.contract.LoginContract;
import com.example.couldmusic.page.main.fragment.MainFragment;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class LoginModel implements LoginContract.LoginModel {

    @Override
    public void login(String phone, String password,final onFinishedListener listener) {
        String address="http://redrock.udday.cn:2022/login/cellphone?phone="+phone+"&password="+password;
        HttpUtil.sendOkHttpRequest(address, new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onRequestFailed();
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final LoginBean loginBean = Utility.handleLoginByPhone(responseText);
                if(loginBean!=null&&loginBean.getCode()==200) {
                    listener.onLoginSucceed(loginBean);
                }else{
                    listener.onLoginFailed();
                }
            }

        });
    }
}
