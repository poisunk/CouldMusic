package com.example.couldmusic.util;

import android.util.Log;

import com.example.couldmusic.bean.BannerBean;
import com.example.couldmusic.bean.CheckMusicBean;
import com.example.couldmusic.bean.HomePageBean;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.bean.LoginStatusBean;
import com.example.couldmusic.bean.PlayListDetailBean;
import com.example.couldmusic.bean.RecommendListBean;
import com.example.couldmusic.bean.SearchHotBean;
import com.example.couldmusic.bean.SearchPlaylistBean;
import com.example.couldmusic.bean.SearchSuggestBean;
import com.example.couldmusic.bean.SearchUsersBean;
import com.example.couldmusic.bean.SongUrlBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.bean.UserDetailBean;
import com.example.couldmusic.bean.UserPlayListBean;
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

    public static PlayListDetailBean handlePlayListDetailInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONObject playlist=jsonObject.getJSONObject("playlist");
            String info=playlist.toString();
            return new Gson().fromJson(info,PlayListDetailBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static SongsDetailBean handleSongsDetailInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info,SongsDetailBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static CheckMusicBean handleCheckMusicInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info,CheckMusicBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }

    public static SongUrlBean handleSongUrlInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info, SongUrlBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static SearchHotBean handleSearchHotInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONObject result=jsonObject.getJSONObject("result");
            String info=result.toString();
            return new Gson().fromJson(info, SearchHotBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }

    public static SearchSuggestBean handleSearchSuggestInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info, SearchSuggestBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }


    public static UserDetailBean handleUserDetailInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info,UserDetailBean.class);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static UserPlayListBean handleUserPlayListInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info,UserPlayListBean.class);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static SongsDetailBean handleSearchSongsDetailInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONObject result=jsonObject.getJSONObject("result");
            String info=result.toString();
            return new Gson().fromJson(info,SongsDetailBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static SearchPlaylistBean handleSearchPlayListInfo(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info,SearchPlaylistBean.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }

    public static SearchUsersBean handleSearchUsersBean(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String info=jsonObject.toString();
            return new Gson().fromJson(info,SearchUsersBean.class);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
