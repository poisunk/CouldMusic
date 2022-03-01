package com.example.couldmusic.page.list.contract;

import android.graphics.Bitmap;

import com.example.couldmusic.bean.CheckMusicBean;
import com.example.couldmusic.bean.PlayListDetailBean;
import com.example.couldmusic.bean.SongsDetailBean;

import java.util.List;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public interface ListContract {

    interface ListView{

        void loadListInfo(PlayListDetailBean bean);

        void loadSongsInfo(SongsDetailBean bean);

        void playMusic(CheckMusicBean bean);

        void loadBackgroundPic(Bitmap bitmap);

        void onRequestFailed();
    }

    interface ListModel{

        interface onFinishedListener{

            void finishPlayListDetail(PlayListDetailBean bean);

            void finishSongsDetail(SongsDetailBean bean);

            void finishMusicCheck(CheckMusicBean bean);

            void finishBackgroundPic(Bitmap bitmap);

            void onRequestFailed();

        }

        void requestPlayListDetail(String address,onFinishedListener listener);

        void requestSongsDetail(String address,onFinishedListener listener);

        void requestMusic(String address,onFinishedListener listener);

        void requestPic(String address,onFinishedListener listener);
    }

    interface ListPresenter{

        void loadPlayListDetail(String listId);

        void loadSongsDetail(PlayListDetailBean bean);

        void loadMusic(int position, List<SongsDetailBean.Song> songs);

        void loadBackgroundPic(String address);
    }
}
