package com.example.couldmusic.page.search.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.UserDetailBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultUserListAdapter extends ArrayAdapter<UserDetailBean.Profile> {

    private int resource;
    private List<UserDetailBean.Profile> objects;

    public ResultUserListAdapter(@NonNull Context context, int resource, @NonNull List<UserDetailBean.Profile> objects) {
        super(context, resource, objects);
        this.resource=resource;
        this.objects=objects;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserDetailBean.Profile profile=getItem(position);

        @SuppressLint("ViewHolder") View view = LayoutInflater.from (getContext ()).inflate (resource, parent, false);

        CircleImageView userAvatar=view.findViewById(R.id.item_user_list_avatar);
        TextView tvName=view.findViewById(R.id.item_user_list_name);
        TextView tvInfo=view.findViewById(R.id.item_user_list_info);

        Glide.with(view).load(profile.getAvatarUrl()).into(userAvatar);
        tvName.setText(profile.getNickname());
        Drawable drawable;
        if(profile.getGender()==1) {
            drawable=view.getResources().getDrawable(R.drawable.user_info_sex_man);
        }else{
            drawable=view.getResources().getDrawable(R.drawable.uaer_info_sex_woman);
        }
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        tvName.setCompoundDrawables(null, null,drawable, null);
        if(profile.getSignature()!="") {
            tvInfo.setText(profile.getSignature());
            tvInfo.setVisibility(View.VISIBLE);
        }


        return view;
    }

    public boolean isEmpty() {
        return objects == null || super.isEmpty();
    }


    @Override
    public int getCount() {
        if(objects==null){
            return 0;
        }
        return super.getCount();
    }
}
