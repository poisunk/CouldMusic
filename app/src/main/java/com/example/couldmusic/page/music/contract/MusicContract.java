package com.example.couldmusic.page.music.contract;

import android.graphics.Bitmap;

import com.example.couldmusic.bean.SongUrlBean;
import com.example.couldmusic.bean.SongsDetailBean;

import java.util.List;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public interface MusicContract {
    interface MusicView{

        void startMusic(int position);

        void showMusicInfo(int position);

        void loadSongsUrl(SongUrlBean songUrlBean);

        void loadBackgroundPic(Bitmap bitmap);

        void onRequestFailed();
    }

    interface MusicModel{

        interface onFinishedListener{
            void finishSongsUrl(SongUrlBean songUrlBean);

            void finishBackgroundPic(Bitmap bitmap);

            void onRequestFailed();
        }

        void requestSongsUrl(String address , onFinishedListener listener);

        void requestPic(String address , onFinishedListener listener);
    }

    interface MusicPresenter{
        void startMusic(int position);

        void showMusicInfo(int position);

        void loadSongsUrl(List<SongsDetailBean.Song> songs);

        void loadBackgroundPic(String address);
    }

}
