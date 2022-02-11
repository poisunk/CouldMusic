package com.example.couldmusic.main.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.bean.LoginStatusBean;
import com.example.couldmusic.bean.UserDetailBean;
import com.example.couldmusic.bean.UserPlayListBean;
import com.example.couldmusic.list.view.ListFragment;
import com.example.couldmusic.login.view.LoginFragment;
import com.example.couldmusic.main.adapter.UserListRecyclerAdapter;
import com.example.couldmusic.main.model.OnItemClickListener;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.example.couldmusic.widget.UserInfo;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;

public class MineFragment extends Fragment implements View.OnClickListener{

    @SuppressLint("StaticFieldLeak")
    private static MineFragment mineFragment=new MineFragment();

    private LoginBean loginBean=null;
    private long userId;
    private long loveListId;
    private boolean isLogin=false;
    private boolean isProgress=false;

    private RecyclerView recyclerView;

    private ImageView ivLoveList;
    private TextView tvLoveListName;
    private TextView tvLoveListInfo;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private UserInfo mUserInfo;

    public static MineFragment newInstance(LoginBean loginBean){
        mineFragment.setLoginBean(loginBean);
        return mineFragment;
    }

    public static MineFragment getInstance(){
        return mineFragment;
    }

    public MineFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLoginStatus();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        load();
    }

    private void initView(View v){
        mSwipeRefreshLayout=v.findViewById(R.id.swipe_mine);
        mUserInfo=v.findViewById(R.id.fragment_mine_user_info);
        ivLoveList=v.findViewById(R.id.fragment_mine_love_list_cover);
        tvLoveListName=v.findViewById(R.id.fragment_mine_love_list_name);
        tvLoveListInfo=v.findViewById(R.id.fragment_mine_love_list_info);
        recyclerView=v.findViewById(R.id.fragment_mine_list);
    }

    private void initEvent(){
        ivLoveList.setOnClickListener(this);
        tvLoveListName.setOnClickListener(this);
        tvLoveListInfo.setOnClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkLoginStatus();
                load();
            }
        });
    }

    private void load(){
        if(isLogin){
            showUserInfo();
        }
    }

    private void checkLoginStatus(){
        if(loginBean==null){
            Toast.makeText(requireContext(),"123",Toast.LENGTH_SHORT).show();
            isLogin=false;
        }else {
            isLogin=true;
            userId=loginBean.getProfile().getUserId();
        }
    }

    private void showUserInfo(){
        isProgress=true;
        String address="http://redrock.udday.cn:2022/user/detail?uid="+userId;
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                        isProgress=false;
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final UserDetailBean userDetailBean=Utility.handleUserDetailInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        assert userDetailBean != null;
                        mUserInfo.setMineInfo(userDetailBean);
                        showUserList();
                    }
                });
            }
        });
    }

    private void showUserList(){
        String address="http://redrock.udday.cn:2022/user/playlist?uid="+userId;
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                        isProgress=false;
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final UserPlayListBean userPlayListBean=Utility.handleUserPlayListInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        isProgress=false;
                        assert userPlayListBean != null;
                        Glide.with(requireActivity()).load(userPlayListBean.getPlaylist().get(0).getCoverImgUrl()).into(ivLoveList);
                        tvLoveListInfo.setText(userPlayListBean.getPlaylist().get(0).getTrackCount()+" 首");
                        tvLoveListName.setText(userPlayListBean.getPlaylist().get(0).getName());
                        loveListId=userPlayListBean.getPlaylist().get(0).getId();
                        UserListRecyclerAdapter adapter=new UserListRecyclerAdapter(
                                userPlayListBean.getPlaylist().subList(1,userPlayListBean.getPlaylist().size()),MineFragment.getInstance());
                        adapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                openList(userPlayListBean.getPlaylist().get(position+1).getId()+"");
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager manager=new LinearLayoutManager(requireContext());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if(!isProgress){
            switch (v.getId()){
                case R.id.fragment_mine_love_list_name:
                case R.id.fragment_mine_love_list_cover:
                case R.id.fragment_mine_love_list_info:
                    openList(loveListId+"");
                    break;
                default:
                    break;
            }
        }
    }

    private void openList(String id){
        FragmentManager manager=requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.included_interface,
                ListFragment.newInstance(id,MainFragment.getInstance()));
        transaction.show(ListFragment.getInstance())
                .hide(MainFragment.getInstance())
                .commit();
    }


    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
