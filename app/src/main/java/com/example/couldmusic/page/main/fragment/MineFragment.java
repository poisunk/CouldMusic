package com.example.couldmusic.page.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.bean.UserDetailBean;
import com.example.couldmusic.bean.UserPlayListBean;
import com.example.couldmusic.page.list.view.ListFragment;
import com.example.couldmusic.page.main.adapter.UserListRecyclerAdapter;
import com.example.couldmusic.page.main.model.OnItemClickListener;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.example.couldmusic.widget.UserInfo;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MineFragment extends Fragment implements View.OnClickListener{

    @SuppressLint("StaticFieldLeak")
    private static MineFragment mineFragment=new MineFragment();

    //查看缓存中是否有登录信息
    private LoginBean loginBean=null;
    private long userId;
    //喜欢歌单的id
    private long loveListId;
    private boolean isLogin=false;
    //是否在加载中 如果是则禁用跳转Fragment的代码
    private boolean isProgress=false;

    //用户歌单（除了喜欢歌单）
    private RecyclerView recyclerView;

    //喜欢歌单信息
    private ImageView ivLoveList;
    private TextView tvLoveListName;
    private TextView tvLoveListInfo;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private UserInfo mUserInfo;

    //需要登录信息
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

    /**
     * 检查登录信息
     * ps:本来是想用网络请求确认是否登录，结果那个借口反应太慢了！！！
     */
    private void checkLoginStatus(){
        if(loginBean==null){
            Toast.makeText(requireContext(),"123",Toast.LENGTH_SHORT).show();
            isLogin=false;
        }else {
            isLogin=true;
            userId=loginBean.getProfile().getUserId();
        }
    }


    /**
     * 加载用户信息
     * 头像，姓名，关注数，粉丝数，等级
     */
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

    /**
     * 加载用户歌单
     */
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

    //打开歌单
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
