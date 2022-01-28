package com.example.couldmusic.util;

import android.text.TextUtils;

import com.example.couldmusic.bean.LoginBean;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public static LoginBean handleLoginByPhone(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String userInfo=jsonObject.toString();
            return GsonUtil.fromJSON(userInfo,LoginBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
