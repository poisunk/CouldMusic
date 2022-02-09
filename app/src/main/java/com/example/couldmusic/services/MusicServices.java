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

        public void playMusic() {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
        }

        public void pauseMusic() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
        }


        public void closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }

        public int getProgress() {
            return mMediaPlayer.getDuration();
        }

        public int getPlayPosition() {
            return mMediaPlayer.getCurrentPosition();
        }

        public void seekToPosition(int msec) {
            mMediaPlayer.seekTo(msec);
        }
    }
}
