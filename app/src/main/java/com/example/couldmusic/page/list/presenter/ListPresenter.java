package com.example.couldmusic.page.list.presenter;

import android.graphics.Bitmap;

import com.example.couldmusic.bean.CheckMusicBean;
import com.example.couldmusic.bean.PlayListDetailBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.list.contract.ListContract;
import com.example.couldmusic.util.BitmapUtil;
import com.google.android.material.badge.BadgeDrawable;

import java.util.List;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class ListPresenter implements ListContract.ListPresenter, ListContract.ListModel.onFinishedListener {

    private ListContract.ListView listView;
    private ListContract.ListModel model;


    public ListPresenter(ListContract.ListView listView,ListContract.ListModel model){
        this.listView=listView;
        this.model=model;
    }

    @Override
    public void loadPlayListDetail(String listId) {
        String address="http://redrock.udday.cn:2022/playlist/detail?id="+listId+"&s=0";
        model.requestPlayListDetail(address,this);
    }

    @Override
    public void loadSongsDetail(PlayListDetailBean playListDetailBean) {
        List<PlayListDetailBean.TrackIds> list=playListDetailBean.getTrackIds();
        int num=list.size()-1;
        String address="http://redrock.udday.cn:2022/song/detail?ids=";
        for(int i=0;i<num;i++){
            address=address+list.get(i).getId()+",";
        }
        address=address+list.get(num).getId();
        model.requestSongsDetail(address,this);
    }

    @Override
    public void loadMusic(int position, List<SongsDetailBean.Song> songs) {
        String address="http://redrock.udday.cn:2022/check/music?id="+songs.get(position).getId();
        model.requestMusic(address,this);
    }

    @Override
    public void loadBackgroundPic(String address) {
        model.requestPic(address,this);
    }

    @Override
    public void finishPlayListDetail(PlayListDetailBean bean) {
        listView.loadListInfo(bean);
        loadSongsDetail(bean);
    }

    @Override
    public void finishSongsDetail(SongsDetailBean bean) {
        listView.loadSongsInfo(bean);
    }

    @Override
    public void finishMusicCheck(CheckMusicBean bean) {
        listView.playMusic(bean);
    }

    @Override
    public void finishBackgroundPic(Bitmap bitmap) {
        listView.loadBackgroundPic(BitmapUtil.doBlur(bitmap,200,false));
    }

    @Override
    public void onRequestFailed() {
        listView.onRequestFailed();
    }
}
