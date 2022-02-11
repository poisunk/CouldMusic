package com.example.couldmusic.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.UserDetailBean;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfo extends ConstraintLayout {

    private final ImageView ivBackground;
    private final ConstraintLayout mUserCard;
    private final CircleImageView civAvatar;
    private final TextView tvName;
    private final TextView tvFollows;
    private final TextView tvFolloweds;
    private final TextView tvLevel;



    public UserInfo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.user_info,this);
        ivBackground=findViewById(R.id.item_user_background);
        mUserCard=findViewById(R.id.item_user_info_card);
        tvFolloweds=findViewById(R.id.item_user_info_followeds);
        tvFollows=findViewById(R.id.item_user_info_follows);
        tvName=findViewById(R.id.item_user_info_name);
        tvLevel=findViewById(R.id.item_user_info_level);
        civAvatar=findViewById(R.id.item_user_info_avatar);
    }

    public void setBackground(String path){
        Glide.with(this).load(path).into(ivBackground);
        ivBackground.setVisibility(View.VISIBLE);
        ConstraintLayout.LayoutParams layoutParams= (LayoutParams) mUserCard.getLayoutParams();
        layoutParams.setMargins(0,150,0,0);
        mUserCard.setLayoutParams(layoutParams);
    }

    public void setMineInfo(UserDetailBean bean){
        setAvatar(bean.getProfile().getAvatarUrl());
        setName(bean.getProfile().getNickname());
        setFolloweds(bean.getProfile().getFolloweds());
        setFollows(bean.getProfile().getFollows());
        setLevel(bean.getLevel());
    }

    public void setAvatar(String path){
        Glide.with(this).load(path).into(civAvatar);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    @SuppressLint("SetTextI18n")
    public void setFollows(int follows){
        tvFollows.setText(follows+" 关注");
    }

    @SuppressLint("SetTextI18n")
    public void setFolloweds(int followeds){
        tvFolloweds.setText(followeds+" 粉丝");
    }

    @SuppressLint("SetTextI18n")
    public void setLevel(int level){
        tvLevel.setText("Lv."+level);
    }


}
