package com.example.couldmusic.main.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.login.view.LoginFragment;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Objects;

public class MainFragment extends Fragment implements View.OnClickListener{

    private final String ARG_LOGIN_BEAN="loginBean";


    public MainFragment(){

    }
    private LoginBean mLoginBean;

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    private LinearLayout mLinearLayout;

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
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

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
                if(mLoginBean==null){
                    FragmentManager fragmentManager= requireActivity().getSupportFragmentManager();
                    Fragment fragment=LoginFragment.newInstance();
                    FragmentTransaction transaction= fragmentManager.beginTransaction();
                    transaction.replace(R.id.included_interface,fragment);
                    transaction.remove(this);
                    transaction.commit();
                }
                break;

        }
    }
}
