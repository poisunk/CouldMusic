package com.example.couldmusic.page.list.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.list.adapter.ListSongsAdapter;
import com.example.couldmusic.page.list.contract.ListContract;
import com.example.couldmusic.page.list.model.ListModel;
import com.example.couldmusic.page.list.presenter.ListPresenter;
import com.example.couldmusic.page.main.model.OnItemClickListener;
import com.example.couldmusic.page.music.MusicFragment;
import com.example.couldmusic.util.BitmapUtil;
import com.example.couldmusic.widget.ListCover;

public class ListFragment extends Fragment implements View.OnClickListener, ListContract.ListView {

    @SuppressLint("StaticFieldLeak")
    private static ListFragment listFragment=new ListFragment();

    private Fragment previousFragment;

    private String listId;

    private boolean isProgress=false;

    private LinearLayout mBackground;
    private TextView tvBarName;
    private TextView tvListName;
    private TextView tvCreatorName;
    private ImageView ivCreatorImage;
    private Button btBack;
    private ListCover mListCover;
    private RecyclerView mRecyclerView;

    private ListPresenter presenter;

    /**
     * 歌单id，打开的fragment
     * @param id
     * @param fragment
     * @return
     */
    public static ListFragment newInstance(String id,Fragment fragment){
        listFragment.setPreviousFragment(fragment);
        listFragment.setListId(id);
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
        presenter=new ListPresenter(this,new ListModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        presenter.loadPlayListDetail(listId);
    }


    private void initView(View v){
        mBackground=v.findViewById(R.id.fragment_play_list);
        tvBarName=v.findViewById(R.id.fragment_play_list_bar_name);
        tvCreatorName=v.findViewById(R.id.fragment_play_list_creator_name);
        tvListName=v.findViewById(R.id.fragment_play_list_name);
        ivCreatorImage=v.findViewById(R.id.fragment_play_list_creator_image);
        btBack=v.findViewById(R.id.fragment_play_list_back_button);
        mListCover=v.findViewById(R.id.fragment_play_list_list_cover);
        mRecyclerView=v.findViewById(R.id.fragment_play_list_recycler);

    }

    private void initEvent(){
        btBack.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if(!isProgress) {
            switch (v.getId()) {
                case R.id.fragment_play_list_back_button:
                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.remove(this).show(previousFragment).commit();
                    break;
            }
        }
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public void setPreviousFragment(Fragment previousFragment) {
        this.previousFragment = previousFragment;
    }

    @Override
    public void loadListInfo(PlayListDetailBean playListDetailBean) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListCover.setImage(playListDetailBean.getCoverImgUrl());
                mListCover.setPlayCount(playListDetailBean.getPlayCount());
                tvListName.setText(playListDetailBean.getName());
                tvCreatorName.setText(playListDetailBean.getCreator().getNickname());
                Glide.with(requireContext()).load(playListDetailBean.getCreator().getAvatarUrl())
                        .into(ivCreatorImage);
            }
        });
        presenter.loadBackgroundPic(playListDetailBean.getCoverImgUrl());
    }

    @Override
    public void loadSongsInfo(SongsDetailBean songsDetailBean) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListSongsAdapter adapter = new ListSongsAdapter(songsDetailBean.getSongs());
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MusicFragment.newInstance(songsDetailBean.getSongs(),position,ListFragment.getInstance());
                        presenter.loadMusic(position,songsDetailBean.getSongs());
                    }
                });
                mRecyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(requireContext());
                mRecyclerView.setLayoutManager(manager);
            }
        });
    }

    @Override
    public void playMusic(CheckMusicBean checkMusicBean) {
        if(checkMusicBean.isSuccess()){
            FragmentManager manager=requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.show(MusicFragment.getInstance());
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

    @Override
    public void loadBackgroundPic(Bitmap bitmap) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBackground.setBackground(new BitmapDrawable(BitmapUtil.blurBitmap(bitmap,requireContext())));
            }
        });
    }

    @Override
    public void onRequestFailed() {
        Toast.makeText(requireContext(),"网络请求失败!",Toast.LENGTH_SHORT).show();
    }
}
