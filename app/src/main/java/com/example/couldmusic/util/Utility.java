package com.example.couldmusic.util;

import android.util.Log;

import com.example.couldmusic.bean.BannerBean;
import com.example.couldmusic.bean.HomePageBean;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.bean.RecommendListBean;
import com.google.gson.Gson;

import org.json.JSONArray;
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

    public static HomePageBean handleHomePageInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONObject data=jsonObject.getJSONObject("data");
            JSONArray blocks=data.getJSONArray("blocks");
            JSONObject block1=blocks.getJSONObject(0);
            JSONObject extInfo=block1.getJSONObject("extInfo");
            String info=extInfo.toString();
            BannerBean bannerBean=new Gson().fromJson(info,BannerBean.class);

            JSONObject block2=blocks.getJSONObject(1);
            String info2=block2.toString();
            RecommendListBean recommendListBean=new Gson().fromJson(info2, RecommendListBean.class);

            HomePageBean homePageBean=new HomePageBean();

            homePageBean.setBannerBean(bannerBean);
            homePageBean.setRecommendListBean(recommendListBean);

            return homePageBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
