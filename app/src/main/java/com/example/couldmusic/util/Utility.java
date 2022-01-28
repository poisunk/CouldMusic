package com.example.couldmusic.util;

import android.util.Log;

import com.example.couldmusic.bean.LoginBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public static LoginBean handleLoginByPhone(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String userInfo=jsonObject.toString();
            return new Gson().fromJson(userInfo,LoginBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
