package com.example.couldmusic.page.search.model;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SearchHotBean;
import com.example.couldmusic.bean.SearchPlaylistBean;
import com.example.couldmusic.bean.SearchSuggestBean;
import com.example.couldmusic.bean.SearchUsersBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.search.adapter.ResultPlaylistListAdapter;
import com.example.couldmusic.page.search.adapter.ResultUserListAdapter;
import com.example.couldmusic.page.search.contract.SearchContract;
import com.example.couldmusic.page.search.view.ResultPlayListFragment;
import com.example.couldmusic.page.search.view.ResultSongsListFragment;
import com.example.couldmusic.page.search.view.ResultUserListFragment;
import com.example.couldmusic.page.search.view.SearchFragment;
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
public class SearchModel implements SearchContract.SearchModel {
    @Override
    public void requestHotInfo(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SearchHotBean searchHotBean= Utility.handleSearchHotInfo(responseText);
                if(searchHotBean!=null) {
                    listener.finishHotInfo(searchHotBean);
                }else {
                    listener.onRequestFailed();
                }
            }
        });
    }

    @Override
    public void requestSuggests(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText = Objects.requireNonNull(response.body()).string();
                final SearchSuggestBean searchSuggestBean = Utility.handleSearchSuggestInfo(responseText);

                if(searchSuggestBean!=null){
                    listener.finishSuggests(searchSuggestBean);
                }else {
                    listener.onRequestFailed();
                }
            }
        });
    }

    @Override
    public void requestPlayListResult(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SearchPlaylistBean searchPlaylistBean=Utility.handleSearchPlayListInfo(responseText);
                listener.finishPlayListResult(searchPlaylistBean);
            }
        });
    }

    @Override
    public void requestSongsListResult(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SongsDetailBean songsDetailBean= Utility.handleSearchSongsDetailInfo(responseText);
                listener.finishSongsListResult(songsDetailBean);
            }
        });
    }

    @Override
    public void requestUserListResult(String address, onFinishedListener listener) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                listener.onRequestFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SearchUsersBean searchUsersBean=Utility.handleSearchUsersBean(responseText);
                listener.finishUserListResult(searchUsersBean);
            }
        });
    }
}
