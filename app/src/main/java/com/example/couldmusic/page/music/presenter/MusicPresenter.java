package com.example.couldmusic.page.music.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.example.couldmusic.bean.SongUrlBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.music.contract.MusicContract;
import com.example.couldmusic.util.BitmapUtil;

import java.util.List;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class MusicPresenter implements MusicContract.MusicPresenter , MusicContract.MusicModel.onFinishedListener {


    private MusicContract.MusicView musicView;
    private MusicContract.MusicModel model;

    public MusicPresenter(MusicContract.MusicView view, MusicContract.MusicModel model){
        this.musicView=view;
        this.model=model;
    }

    @Override
    public void startMusic(int position) {
        musicView.startMusic(position);
    }

    @Override
    public void showMusicInfo(int position) {
        musicView.showMusicInfo(position);
    }

    @Override
    public void loadSongsUrl(List<SongsDetailBean.Song> songs) {
        String address="http://redrock.udday.cn:2022/song/url?id=";
        address+=songs.get(0).getId();
        for(int i=1;i<songs.size();i++){
            address = address + "," +songs.get(i).getId();
        }
        model.requestSongsUrl(address,this);
    }

    @Override
    public void loadBackgroundPic(String address) {
        model.requestPic(address,this);
    }

    @Override
    public void finishSongsUrl(SongUrlBean songUrlBean) {
        musicView.loadSongsUrl(songUrlBean);
    }

    @Override
    public void finishBackgroundPic(Bitmap bitmap) {
        musicView.loadBackgroundPic(BitmapUtil.doBlur(bitmap,200,false));
    }

    @Override
    public void onRequestFailed() {
        musicView.onRequestFailed();
    }
}
