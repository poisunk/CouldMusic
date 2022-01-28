package com.example.couldmusic.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.couldmusic.R;
import com.example.couldmusic.base.BaseFragment;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.login.contract.LoginContract;

public class MainFragment extends BaseFragment {


    public MainFragment(){

    }

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initEvent();
    }

    private void initView(View v){
        mDrawerLayout=(DrawerLayout) v.findViewById(R.id.content_drawer_layout);
        mToolbar=(Toolbar) v.findViewById(R.id.content_main_tool_bar);
        mLinearLayout=(LinearLayout) v.findViewById(R.id.content_left_menu_bar);
    }

    private void initEvent(){
        mLinearLayout.setOnClickListener(this);
        initDrawerLayout();
    }

    private void initDrawerLayout(){
        Activity activity=requireActivity();
        //mDrawerLayout与mToolbar关联起来
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout, mToolbar, R.string.open, R.string.close);
        //初始化状态
        actionBarDrawerToggle.syncState();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.content_left_menu_bar:

                break;
        }
    }

}