package com.example.couldmusic.page.list.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.example.couldmusic.bean.CheckMusicBean;
import com.example.couldmusic.bean.PlayListDetailBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.list.contract.ListContract;
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
public class ListModel implements ListContract.ListModel {


    @Override
    public void requestPlayListDetail(String address,onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final PlayListDetailBean playListDetailBean= Utility.handlePlayListDetailInfo(responseText);
                if(playListDetailBean!=null){
                    listener.finishPlayListDetail(playListDetailBean);
                }else{
                    listener.onRequestFailed();
                }
            }
        });
    }



    @Override
    public void requestSongsDetail(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SongsDetailBean songsDetailBean=Utility.handleSongsDetailInfo(responseText);
                if(songsDetailBean!=null){
                    listener.finishSongsDetail(songsDetailBean);
                }else{
                    listener.onRequestFailed();
                }
            }
        });
    }

    @Override
    public void requestMusic(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final CheckMusicBean checkMusicBean=Utility.handleCheckMusicInfo(responseText);
                if(checkMusicBean!=null){
                    listener.finishMusicCheck(checkMusicBean);
                }else {
                    listener.onRequestFailed();
                }
            }
        });
    }

    @Override
    public void requestPic(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final byte[] responseBytes= Objects.requireNonNull(response.body()).bytes();
                final Bitmap bitmap= BitmapFactory.decodeByteArray(responseBytes,0,responseBytes.length);
                if(bitmap!=null){
                    listener.finishBackgroundPic(bitmap);
                }else {
                    listener.onRequestFailed();
                }
            }
        });
    }
}
