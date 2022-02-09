package com.example.couldmusic.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.couldmusic.R;

public class CommunityFragment extends Fragment {

    private static CommunityFragment communityFragment=new CommunityFragment();


    public static CommunityFragment newInstance(){
        communityFragment=new CommunityFragment();
        return communityFragment;
    }

    public CommunityFragment getInstance(){
        return communityFragment;
    }

    public CommunityFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
