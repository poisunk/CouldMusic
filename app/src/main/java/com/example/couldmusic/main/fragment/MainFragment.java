package com.example.couldmusic.main.fragment;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.login.view.LoginFragment;
import com.example.couldmusic.main.adapter.MainViewPaperAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragment extends Fragment implements View.OnClickListener{

    private final String ARG_LOGIN_BEAN="loginBean";

    private final ArrayList<Fragment> fragments=new ArrayList<>();

    private final String[] tabName={"发现","我的","云村"};


    public MainFragment(){

    }
    private LoginBean mLoginBean;

    private ViewPager2 mViewPaper2;
    private TabLayout mTabLayout;



    private MaterialButton mbCancelLogin;
    private TextView tvLoginUser;
    private CircleImageView civLoginUser;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private LinearLayout llMenuBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBundle();
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
        initPage();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initLoginInfo();
    }


    private void initBundle(){
        Bundle args=getArguments();
        if(args!=null){
            Serializable obj = args.getSerializable("loginBean");
            if (obj instanceof LoginBean) {
                mLoginBean = (LoginBean) obj;
                SharedPreferences.Editor editor= PreferenceManager.
                        getDefaultSharedPreferences(requireContext()).edit();
                editor.putString(ARG_LOGIN_BEAN,new Gson().toJson(mLoginBean));
                editor.apply();
            }
        }
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
    }

    private void initEvent(){
        llMenuBar.setOnClickListener(this);
        mbCancelLogin.setOnClickListener(this);
        initDrawerLayout();
    }

    private void initPage(){
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
        fragments.add(new CommunityFragment());
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

    private void initDrawerLayout(){
        Activity activity=requireActivity();
        //mDrawerLayout与mToolbar关联起来
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout, mToolbar, R.string.open, R.string.close);
        //初始化状态
        actionBarDrawerToggle.syncState();
    }

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
            case R.id.content_left_menu_bar:
                if(mLoginBean==null){
                    FragmentManager fragmentManager= requireActivity().getSupportFragmentManager();
                    Fragment fragment=LoginFragment.newInstance();
                    FragmentTransaction transaction= fragmentManager.beginTransaction();
                    transaction.add(R.id.included_interface,fragment);
                    transaction.remove(this);
                    transaction.commit();
                }
                break;
            case R.id.content_left_menu_button_cancel_login:
                if (mLoginBean!=null){
                    mLoginBean=null;
                    SharedPreferences.Editor editor= PreferenceManager.
                            getDefaultSharedPreferences(requireContext()).edit();
                    editor.remove(ARG_LOGIN_BEAN);
                    editor.apply();


                    initLoginInfo();
                }
                break;

        }
    }
}
