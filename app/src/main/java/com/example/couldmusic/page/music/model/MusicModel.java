package com.example.couldmusic.page.music.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.example.couldmusic.bean.SongUrlBean;
import com.example.couldmusic.page.music.contract.MusicContract;
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
public class MusicModel implements MusicContract.MusicModel {
    @Override
    public void requestSongsUrl(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SongUrlBean songUrlBean= Utility.handleSongUrlInfo(responseText);
                if(songUrlBean!=null&&songUrlBean.getData()!=null){
                    listener.finishSongsUrl(songUrlBean);
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
