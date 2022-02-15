package com.example.couldmusic.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;


public class MusicServices extends Service {


    private MusicBinder mBinder=new MusicBinder();

    private MediaPlayer mMediaPlayer=new MediaPlayer();



    public MusicServices(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MusicBinder extends Binder{

        //开始播放一个新的音乐
        public void startMusic(String url) {
            if (mMediaPlayer != null) {
                try {
                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(url);
                    mMediaPlayer.prepare();
                    playMusic();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //开始播放
        public void playMusic() {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
        }

        //暂停
        public void pauseMusic() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
        }

        //关闭
        public void closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }

        //得到全部进度
        public int getProgress() {
            return mMediaPlayer.getDuration();
        }

        //得到当前进度
        public int getPlayPosition() {
            return mMediaPlayer.getCurrentPosition();
        }

        //跳转到指定进度
        public void seekToPosition(int msec) {
            mMediaPlayer.seekTo(msec);
        }
    }
}
