package com.example.couldmusic.page.main.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.page.login.view.LoginFragment;
import com.example.couldmusic.page.main.adapter.MainViewPaperAdapter;
import com.example.couldmusic.page.search.view.SearchFragment;
import com.example.couldmusic.util.HttpUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainFragment extends Fragment implements View.OnClickListener{


    /**
     * 每个Fragment都有一个static fragment
     * 以便于Fragment间的切换
     */
    @SuppressLint("StaticFieldLeak")
    private final static MainFragment mainFragment=new MainFragment();

    private final String ARG_LOGIN_BEAN="loginBean";

    private final ArrayList<Fragment> fragments=new ArrayList<>();

    /**
     * Tab的命名
     */
    private final String[] tabName={"发现","我的","云村"};



    private LoginBean mLoginBean;

    private ViewPager2 mViewPaper2;
    private TabLayout mTabLayout;


    private CardView cvSearch;
    private MaterialButton mbCancelLogin;
    private TextView tvLoginUser;
    private CircleImageView civLoginUser;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private LinearLayout llMenuBar;

    public static MainFragment newInstance(){
        return mainFragment;
    }

    public static MainFragment getInstance(){
        return mainFragment;
    }

    public MainFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 检查缓存中有没有储存的登录数据
         */
        if(mLoginBean==null){
            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(requireContext());
            String loginBean=preferences.getString(ARG_LOGIN_BEAN,null);
            if(loginBean!=null){
                mLoginBean=new Gson().fromJson(loginBean,LoginBean.class);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_content,container,false);
        initView(v);
        initEvent();
        fragments.clear();
        initPage();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        initLoginInfo();
    }


    private void initView(View v){
        tvLoginUser= v.findViewById(R.id.content_left_menu_bar_name);
        civLoginUser= v.findViewById(R.id.content_left_menu_bar_pic);
        mDrawerLayout= v.findViewById(R.id.fragment_content_drawer_layout);
        mToolbar= v.findViewById(R.id.content_main_tool_bar);
        mbCancelLogin= v.findViewById(R.id.content_left_menu_button_cancel_login);
        llMenuBar= v.findViewById(R.id.content_left_menu_bar);
        mViewPaper2= v.findViewById(R.id.content_main_view_paper2);
        mTabLayout= v.findViewById(R.id.content_main_tab_layout);
        cvSearch=v.findViewById(R.id.fragment_content_main_search);
    }

    private void initEvent(){
        llMenuBar.setOnClickListener(this);
        mbCancelLogin.setOnClickListener(this);
        cvSearch.setOnClickListener(this);
        initDrawerLayout();
    }


    /**
     * 初始化页面信息
     */
    private void initPage(){
        fragments.add(DiscoverFragment.newInstance());
        fragments.add(MineFragment.newInstance(mLoginBean));
        fragments.add(CommunityFragment.newInstance());
        MainViewPaperAdapter adapter=new MainViewPaperAdapter(requireActivity(),fragments);
        mViewPaper2.setAdapter(adapter);

        new TabLayoutMediator(mTabLayout, mViewPaper2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position)
                    {
                        tab.setText(tabName[position]);
                    }
                }).attach();

    }

    /**
     * 初始化侧边栏
     */
    private void initDrawerLayout(){
        Activity activity=requireActivity();
        //mDrawerLayout与mToolbar关联起来
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout, mToolbar, R.string.open, R.string.close);
        //初始化状态
        actionBarDrawerToggle.syncState();
    }

    /**
     * 初始化登录信息
     */
    private void initLoginInfo(){
        if(mLoginBean!=null){
            String pic=mLoginBean.getProfile().getAvatarUrl();
            Glide.with(requireActivity()).load(pic).into(civLoginUser);
            tvLoginUser.setText(mLoginBean.getProfile().getNickname());
        }else{
            tvLoginUser.setText("立即登录>");
            Glide.with(requireActivity()).load(R.drawable.music_circle).into(civLoginUser);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /**
             * 如果没有登录就转到登录界面
             */
            case R.id.content_left_menu_bar:
                if(mLoginBean==null){
                    FragmentManager fragmentManager= requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction= fragmentManager.beginTransaction();
                    transaction.add(R.id.included_interface,LoginFragment.newInstance(),"LoginFragment");
                    transaction.addToBackStack("LoginFragment");
                    transaction.commit();
                }
                break;
            /**
             * 取消登录按钮
             */
            case R.id.content_left_menu_button_cancel_login:
                if (mLoginBean!=null){
                    mLoginBean=null;
                    SharedPreferences.Editor editor= PreferenceManager.
                            getDefaultSharedPreferences(requireContext()).edit();
                    editor.remove(ARG_LOGIN_BEAN);
                    editor.apply();

                    cancelLogin();
                    initLoginInfo();
                }
                break;
            /**
             * 搜索框
             */
            case R.id.fragment_content_main_search:
                FragmentManager manager=requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.add(R.id.included_interface, SearchFragment.getInstance(),"SearchFragment");
                transaction.addToBackStack("SearchFragment");
                transaction.commit();
                break;

        }
    }


    /**
     * 取消登录的网络请求
     */
    private void cancelLogin(){
        String address="http://redrock.udday.cn:2022/logout";
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
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"取消登录成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
