package com.example.couldmusic.page.music.view;

import static android.content.Context.BIND_AUTO_CREATE;

import static com.example.couldmusic.R.drawable.song_pausecircle;
import static com.example.couldmusic.R.drawable.song_playcircle;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.SongUrlBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.music.contract.MusicContract;
import com.example.couldmusic.page.music.model.MusicModel;
import com.example.couldmusic.page.music.presenter.MusicPresenter;
import com.example.couldmusic.services.MusicServices;
import com.example.couldmusic.util.BitmapUtil;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MusicFragment extends Fragment implements View.OnClickListener, MusicContract.MusicView {

    @SuppressLint("StaticFieldLeak")
    private static MusicFragment musicFragment=new MusicFragment();

    private SongUrlBean songUrlBean;
    private List<SongsDetailBean.Song> songs;
    private int position;
    //是否正在播放
    private boolean isPlaying=false;
    private boolean isProgress=false;


    private MusicServices.MusicBinder mBinder;

    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    private Handler mHandler = new Handler();

    //退出按钮
    private Button bBack;
    private LinearLayout llBackground;
    //当前播放音乐的名字
    private TextView tvMusicName;
    //当前播放音乐作者的名字
    private TextView tvArName;
    //音乐播放的Cd背景
    private CircleImageView civCD;
    private CircleImageView civMusic;
    //当前播放的时间
    private TextView tvSeekTime;
    //音乐时长
    private TextView tvEndTime;
    //拖动进度条
    private SeekBar sbProgress;
    //下一首
    private ImageButton ibNext;
    //播放、暂停
    private ImageButton ibPlay;
    //上一首
    private ImageButton ibPrevious;

    private MusicPresenter presenter;

    /**
     * songs当前歌单的所有歌曲信息，position当前播放的位置，fragment打开的位置
     * @param songs
     * @param position
     * @return
     */
    public static MusicFragment newInstance(List<SongsDetailBean.Song> songs,int position) {
        //如果点击同一个歌单就没必要在加载
        if(musicFragment.getSongs() == null || !musicFragment.getSongs().equals(songs)){
            musicFragment.setSongs(songs);
        }
        musicFragment.setPosition(position);
        return musicFragment;
    }

    public static MusicFragment getInstance(){
        return musicFragment;
    }

    public MusicFragment(){

    }

    private Intent MusicServiceIntent;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder=(MusicServices.MusicBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MusicServiceIntent=new Intent(requireContext(),MusicServices.class);
        //判断有没有权限
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            //有就绑定播放音乐的服务
            requireActivity().bindService(MusicServiceIntent, serviceConnection, BIND_AUTO_CREATE);
        }

        presenter=new MusicPresenter(this,new MusicModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_music,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        presenter.loadSongsUrl(songs);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //当从隐藏中展现时在加载
        if(!hidden) {
            presenter.showMusicInfo(position);
            presenter.startMusic(position);
        }
    }

    private void initView(View v){
        bBack=v.findViewById(R.id.fragment_play_music_back);
        llBackground=v.findViewById(R.id.fragment_play_music_background);
        tvMusicName=v.findViewById(R.id.fragment_play_music_music_name);
        tvArName=v.findViewById(R.id.fragment_play_music_ar_name);
        civCD=v.findViewById(R.id.fragment_play_music_cd);
        civMusic=v.findViewById(R.id.fragment_play_music_image);
        tvSeekTime=v.findViewById(R.id.fragment_play_music_seek_time);
        tvEndTime=v.findViewById(R.id.fragment_play_music_end_time);
        sbProgress=v.findViewById(R.id.fragment_play_music_seek_bar);
        ibNext=v.findViewById(R.id.fragment_play_music_next);
        ibPlay=v.findViewById(R.id.fragment_play_music_play);
        ibPrevious=v.findViewById(R.id.fragment_play_music_previous);
    }

    private void initEvent(){
        bBack.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibPlay.setOnClickListener(this);
        ibPrevious.setOnClickListener(this);

        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //如果改变来自用户则改变播放进度
                if(fromUser){
                    mBinder.seekToPosition(seekBar.getProgress());
                }
                //如果进度完成则播放下一首
                if(progress==seekBar.getMax()){
                    presenter.startMusic(position);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }



    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    @Override
    public void onClick(View v) {
        if(!isProgress) {
            switch (v.getId()) {
                case R.id.fragment_play_music_back:
                    back();
                    break;
                case R.id.fragment_play_music_next:
                    presenter.startMusic(position = (position + 1) % songs.size());
                    break;
                case R.id.fragment_play_music_play:
                    if(isPlaying) {
                        ibPlay.setImageDrawable(getResources().getDrawable(song_playcircle));
                        pause();
                    }else {
                        ibPlay.setImageDrawable(getResources().getDrawable(song_pausecircle));
                        play();
                    }
                    break;
                case R.id.fragment_play_music_previous:
                    presenter.startMusic(position = (position == 0 ? (songs.size() - 1) : (position - 1)));
                    break;
            }
        }
    }

    //返回到previousFragment
    private void back(){
        if(!isProgress) {
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            manager.popBackStackImmediate();
        }
    }

    /**
     * 开始播放position的音乐
     * @param position
     */
    public void startMusic(int position){
        if(!isProgress) {
            Toast.makeText(requireContext(), "开始播放", Toast.LENGTH_SHORT).show();
            showMusicInfo(position);
            mBinder.startMusic(songUrlBean.getData().get(position).getUrl());
            sbProgress.setMax(mBinder.getProgress());
            tvEndTime.setText(time.format(mBinder.getProgress()));
            //开启一个新线程去改变进度条
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    sbProgress.setProgress(mBinder.getPlayPosition());
                    tvSeekTime.setText(time.format(mBinder.getPlayPosition()));
                    mHandler.postDelayed(this, 1000);
                }
            });
            isPlaying=true;
        }
    }

    //播放音乐
    private void play(){
        mBinder.playMusic();
        isPlaying=true;
    }

    //暂停音乐
    private void pause(){
        mBinder.pauseMusic();
        isPlaying=false;
    }

    //加载歌曲信息
    public void showMusicInfo(int position){
        tvMusicName.setText(songs.get(position).getName());
        String alPicUrl;
        //搜索获得的命名与歌单中获得的不一样，因此这里要分开处理
        if(songs.get(position).getArtists()==null) {
            String arName = songs.get(position).getAr().get(0).getName();
            for (int i = 1; i < songs.get(position).getAr().size(); i++) {
                arName = arName + "," + songs.get(position).getAr().get(i).getName();
            }
            tvArName.setText(arName);
            alPicUrl=songs.get(position).getAl().getPicUrl();


        }else{
            String arName = songs.get(position).getArtists().get(0).getName();
            for (int i = 1; i < songs.get(position).getArtists().size(); i++) {
                arName = arName + "," + songs.get(position).getArtists().get(i).getName();
            }
            tvArName.setText(arName);

            alPicUrl=songs.get(position).getAlbum().getArtist().getImg1v1Url();
        }
        Glide.with(this).load(alPicUrl).into(civMusic);
        //获取图片的byte信息以获取一个Bitmap
        presenter.loadBackgroundPic(alPicUrl);
    }



    public void setSongs(List<SongsDetailBean.Song> songs) {
        this.songs = songs;
    }

    public List<SongsDetailBean.Song> getSongs() {
        return songs;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSongUrlBean(SongUrlBean songUrlBean) {
        this.songUrlBean = songUrlBean;
    }

    @Override
    public void loadSongsUrl(SongUrlBean songUrlBean) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MusicFragment.getInstance().setSongUrlBean(songUrlBean);
                presenter.startMusic(position);
            }
        });

    }

    @Override
    public void loadBackgroundPic(Bitmap bitmap) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                llBackground.setBackground(new BitmapDrawable(BitmapUtil.blurBitmap(bitmap,requireContext())));
            }
        });
    }

    @Override
    public void onRequestFailed() {
        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
    }
}
