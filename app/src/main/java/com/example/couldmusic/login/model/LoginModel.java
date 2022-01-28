package com.example.couldmusic.login.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.login.contract.LoginContract;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LoginModel implements LoginContract.Model {





    @Override
    public LoginBean login(String phone, String password) {
        final LoginBean[] loginBean = {null};
        String address="http://redrock.udday.cn:2022/login/cellphone?phone="+phone+"&password="+password;
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseText=response.body().string();
                loginBean[0] =Utility.handleLoginByPhone(responseText);
            }
        });
        return loginBean[0];
    }


}
