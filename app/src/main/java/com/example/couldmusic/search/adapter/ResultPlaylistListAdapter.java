package com.example.couldmusic.search.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.PlayListDetailBean;

import java.util.List;

public class ResultPlaylistListAdapter extends ArrayAdapter<PlayListDetailBean> {

    private int resource;
    private List<PlayListDetailBean> objects;

    public ResultPlaylistListAdapter(@NonNull Context context, int resource, @NonNull List<PlayListDetailBean> objects) {
        super(context, resource, objects);
        this.resource=resource;
        this.objects=objects;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlayListDetailBean bean=getItem(position);

        @SuppressLint("ViewHolder") View view = LayoutInflater.from (getContext ()).inflate (resource, parent, false);

        ImageView ivCover=view.findViewById(R.id.item_list_cover);
        TextView tvListName=view.findViewById(R.id.item_list_name);
        TextView tvListInfo=view.findViewById(R.id.item_list_info);

        Glide.with(view).load(bean.getCoverImgUrl()).into(ivCover);
        tvListName.setText(bean.getName());
        tvListInfo.setText(bean.getTrackCount()+"首,by "+bean.getCreator().getNickname()+", 播放"+countString(bean.getPlayCount()));

        return view;
    }

    /**
     * super中判断是否为空是以getCount()==0
     * @return
     */
    @Override
    public boolean isEmpty() {
        return objects == null || super.isEmpty();
    }

    /**
     * super中的getCount()使用了list.size()，有时会有空指针
     * @return
     */
    @Override
    public int getCount() {
        if(objects==null){
            return 0;
        }
        return super.getCount();
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
