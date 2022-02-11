package com.example.couldmusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;


public class ListCover extends FrameLayout {

    private final ImageView mImageView;
    private final TextView mTextView;
    private final TextView mTitle;


    public ListCover(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.list_cover,this);
        mImageView=findViewById(R.id.item_list_cover_pic);
        mTextView=findViewById(R.id.item_list_cover_count);
        mTitle=findViewById(R.id.item_list_cover_title);
    }

    public void setTitle(String title){
        mTitle.setText(title);
    }


    public void setImage(String path){
        Glide.with(this).load(path).into(mImageView);
    }

    public void setPlayCount(long count){
        String countStr=countString(count);
        mTextView.setText(countStr);
        mTextView.setVisibility(VISIBLE);
    }

    private String countString(long count){
        if(count==0L){
            return "";
        }else if(count<10000){
            return String.valueOf(count);
        }else if(count<10000*10000){
            long start=count/10000;
            long end=count%10000/1000;
            if(end==0){
                return start+"万";
            }else{
                return start+"."+end+"万";
            }
        }else if(count< 9999L *10000000){
            long start=count/100000000;
            long end=count%100000000/10000000;
            if(end==0){
                return start+"亿";
            }else{
                return start+"."+end+"亿";
            }
        }else{
            return "999.9亿+";
        }

    }
}
