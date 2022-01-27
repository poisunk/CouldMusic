package com.example.couldmusic.page.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.couldmusic.R;

public class MainFragment extends Fragment {


    public MainFragment(){

    }

    private DrawerLayout mDrawerLayout;

    private CardView mCardView;
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initDrawerLayout();
    }

    private void initView(View v){
        mDrawerLayout=(DrawerLayout) v.findViewById(R.id.content_drawer_layout);
        mCardView=(CardView) v.findViewById(R.id.content_card);
        mToolbar=(Toolbar) v.findViewById(R.id.content_main_tool_bar);

    }

    private void initDrawerLayout(){
        Activity activity=requireActivity();
        //mDrawerLayout与mToolbar关联起来
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout, mToolbar, R.string.open, R.string.close);
        //初始化状态
        actionBarDrawerToggle.syncState();
    }
}
