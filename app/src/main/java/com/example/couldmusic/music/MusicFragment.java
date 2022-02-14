package com.example.couldmusic.music;

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
import android.graphics.drawable.Drawable;
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
import com.example.couldmusic.list.view.ListFragment;
import com.example.couldmusic.services.MusicServices;
import com.example.couldmusic.util.BitmapUtil;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MusicFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static MusicFragment musicFragment=new MusicFragment();

    private Fragment previousFragment;

    private SongUrlBean songUrlBean;
    private List<SongsDetailBean.Song> songs;
    private int position;
    private boolean isPlaying=false;
    private boolean isProgress=false;


    private MusicServices.MusicBinder mBinder;

    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    private Handler mHandler = new Handler();

    private Button bBack;
    private LinearLayout llBackground;
    private TextView tvMusicName;
    private TextView tvArName;
    private CircleImageView civCD;
    private CircleImageView civMusic;
    private TextView tvSeekTime;
    private TextView tvEndTime;
    private SeekBar sbProgress;
    private ImageButton ibNext;
    private ImageButton ibPlay;
    private ImageButton ibPrevious;


    public static MusicFragment newInstance(List<SongsDetailBean.Song> songs,int position,Fragment fragment) {
        if(musicFragment.getSongs() == null || !musicFragment.getSongs().equals(songs)){
            musicFragment.setPosition(position);
            musicFragment.setSongs(songs);
            musicFragment.loadSongUrl();
        }
        musicFragment.setPreviousFragment(fragment);
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
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {

            show();
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
                if(fromUser){
                    mBinder.seekToPosition(seekBar.getProgress());
                }
                if(progress==seekBar.getMax()){
                    startMusic(position = (position + 1) % songs.size());
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

    private void show(){
        if(songs!=null) {
            showMusicInfo(position);
        }
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
                    startMusic(position = (position + 1) % songs.size());
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
                    startMusic(position = (position == 0 ? (songs.size() - 1) : (position - 1)));
                    break;
            }
        }
    }

    private void back(){
        if(!isProgress) {
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(this).show(previousFragment);
            transaction.commit();
        }
    }

    private void loadMusicInfo(){

    }

    private void startMusic(int position){
        if(!isProgress) {
            Toast.makeText(requireContext(), "开始播放", Toast.LENGTH_SHORT).show();
            showMusicInfo(position);
            mBinder.startMusic(songUrlBean.getData().get(position).getUrl());
            sbProgress.setMax(mBinder.getProgress());
            tvEndTime.setText(time.format(mBinder.getProgress()));
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

    private void play(){
        mBinder.playMusic();
        isPlaying=true;
    }

    private void pause(){
        mBinder.pauseMusic();
        isPlaying=false;
    }

    private void showMusicInfo(int position){
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
        HttpUtil.sendOkHttpRequest(alPicUrl, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                        isProgress=false;
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final byte[] responseBytes= Objects.requireNonNull(response.body()).bytes();
                final Bitmap bitmap= BitmapFactory.decodeByteArray(responseBytes,0,responseBytes.length);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        llBackground.setBackground(new BitmapDrawable(BitmapUtil.blurBitmap(bitmap,requireContext())));
                    }
                });
            }
        });
    }

    private void loadSongUrl(){
        isProgress=true;
        String address="http://redrock.udday.cn:2022/song/url?id=";
        address+=songs.get(0).getId();
        for(int i=1;i<songs.size();i++){
            address = address + "," +songs.get(i).getId();
        }
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                        isProgress=false;
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SongUrlBean songUrlBean= Utility.handleSongUrlInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isProgress=false;
                        MusicFragment.getInstance().setSongUrlBean(songUrlBean);
                        MusicFragment.getInstance().startMusic(position);
                    }
                });
            }
        });

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

    public void setPreviousFragment(Fragment previousFragment) {
        this.previousFragment = previousFragment;
    }
}
