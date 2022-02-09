package com.example.couldmusic.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.couldmusic.R;

public class MineFragment extends Fragment {

    private static MineFragment mineFragment=new MineFragment();

    public static MineFragment newInstance(){
        mineFragment=new MineFragment();
        return mineFragment;
    }

    public MineFragment getInstance(){
        return mineFragment;
    }

    public MineFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
