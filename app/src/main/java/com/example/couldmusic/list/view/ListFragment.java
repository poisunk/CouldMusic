package com.example.couldmusic.list.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.CheckMusicBean;
import com.example.couldmusic.bean.PlayListDetailBean;
import com.example.couldmusic.bean.RecommendListBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.main.fragment.MainFragment;
import com.example.couldmusic.main.model.OnItemClickListener;
import com.example.couldmusic.list.adapter.ListSongsAdapter;
import com.example.couldmusic.music.MusicFragment;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.example.couldmusic.widget.ListCover;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static ListFragment listFragment=new ListFragment();

    private RecommendListBean.Creatives creatives;
    private String listId;

    private boolean isProgress=false;

    private TextView tvBarName;
    private TextView tvListName;
    private TextView tvCreatorName;
    private ImageView ivCreatorImage;
    private Button btBack;
    private ListCover mListCover;
    private RecyclerView mRecyclerView;

    public static ListFragment newInstance(){
        return listFragment;
    }

    public static ListFragment getInstance(){
        return listFragment;
    }

    public ListFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args=getArguments();
        if(args!=null){
            Serializable obj = args.getSerializable("listBean");
            if (obj instanceof RecommendListBean.Creatives){
                creatives=(RecommendListBean.Creatives) obj;
            }
            args.clear();
        }
        return inflater.inflate(R.layout.fragment_play_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        load();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            Bundle args = getArguments();
            if (args != null) {
                Serializable obj = args.getSerializable("listBean");
                if (obj instanceof RecommendListBean.Creatives) {
                    creatives = (RecommendListBean.Creatives) obj;
                }
                args.clear();
            }
            if (creatives != null) {
                mListCover.setPlayCount(creatives.getResources().get(0).getResourceExtInfo().getPlayCount());
                mListCover.setImage(requireContext(), creatives.getUiElement().getImage().getImageUrl());
                tvListName.setText(creatives.getUiElement().getMainTitle().getTitle());
                listId = creatives.getCreativeId();
            }
            load();
        }
    }

    private void initView(View v){
        tvBarName=v.findViewById(R.id.fragment_play_list_bar_name);
        tvCreatorName=v.findViewById(R.id.fragment_play_list_creator_name);
        tvListName=v.findViewById(R.id.fragment_play_list_name);
        ivCreatorImage=v.findViewById(R.id.fragment_play_list_creator_image);
        btBack=v.findViewById(R.id.fragment_play_list_back_button);
        mListCover=v.findViewById(R.id.fragment_play_list_list_cover);
        mRecyclerView=v.findViewById(R.id.fragment_play_list_recycler);

        if (creatives!=null){
            mListCover.setPlayCount(creatives.getResources().get(0).getResourceExtInfo().getPlayCount());
            mListCover.setImage(requireContext(),creatives.getUiElement().getImage().getImageUrl());
            tvListName.setText(creatives.getUiElement().getMainTitle().getTitle());
            listId=creatives.getCreativeId();
        }
    }

    private void initEvent(){
        btBack.setOnClickListener(this);
    }


    private void load(){
        isProgress=true;
        String address="http://redrock.udday.cn:2022/playlist/detail?id="+listId+"&s=0";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final PlayListDetailBean playListDetailBean= Utility.handlePlayListDetailInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (playListDetailBean!=null) {
                            tvCreatorName.setText(playListDetailBean.getCreator().getNickname());
                            Glide.with(requireContext()).load(playListDetailBean.getCreator().getAvatarUrl())
                                    .into(ivCreatorImage);
                            loadListDetail(playListDetailBean);
                        }
                    }
                });
            }
        });
    }


    private void loadListDetail(PlayListDetailBean playListDetailBean){
        List<PlayListDetailBean.TrackIds> list=playListDetailBean.getTrackIds();
        int num=list.size()-1;
        String address="http://redrock.udday.cn:2022/song/detail?ids=";
        for(int i=0;i<num;i++){
            address=address+list.get(i).getId()+",";
        }
        address=address+list.get(num).getId();
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
                final SongsDetailBean songsDetailBean=Utility.handleSongsDetailInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(songsDetailBean!=null) {
                            ListSongsAdapter adapter = new ListSongsAdapter(songsDetailBean.getSongs());
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    playMusic(position, songsDetailBean.getSongs());
                                }
                            });
                            mRecyclerView.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(requireContext());
                            mRecyclerView.setLayoutManager(manager);
                            isProgress=false;
                        }
                    }
                });
            }
        });
    }

    private void playMusic(int position, List<SongsDetailBean.Song> songs){
        String address="http://redrock.udday.cn:2022/check/music?id="+songs.get(position).getId();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final CheckMusicBean checkMusicBean=Utility.handleCheckMusicInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(checkMusicBean.isSuccess()){
                            FragmentManager manager=requireActivity().getSupportFragmentManager();
                            FragmentTransaction transaction=manager.beginTransaction();
                            transaction.show(MusicFragment.newInstance(songs,position));
                            transaction.hide(ListFragment.getInstance());
                            transaction.commit();
                        }else {
                            AlertDialog.Builder builder=new AlertDialog.Builder(requireContext());
                            builder.setTitle("提示");
                            builder.setMessage(checkMusicBean.getMessage());
                            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }
                    }
                });
            }
        });
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_play_list_back_button:
                if(!isProgress) {
                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.remove(this).show(MainFragment.getInstance()).commit();
                }
                break;
        }
    }
}
