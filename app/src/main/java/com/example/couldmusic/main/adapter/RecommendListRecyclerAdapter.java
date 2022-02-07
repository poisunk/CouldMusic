package com.example.couldmusic.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.RecommendListBean;
import com.example.couldmusic.main.model.OnItemClickListener;

public class RecommendListRecyclerAdapter extends RecyclerView.Adapter<RecommendListRecyclerAdapter.InnerHolder>{

    private RecommendListBean bean;
    private Fragment fragment;
    private OnItemClickListener mClickListener;


    public RecommendListRecyclerAdapter(RecommendListBean bean, Fragment fragment){
        this.bean=bean;
        this.fragment=fragment;
    }
    @NonNull
    @Override
    public RecommendListRecyclerAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cover,
                parent, false),mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendListRecyclerAdapter.InnerHolder holder, int position) {
        Glide.with(fragment.requireContext()).load(bean.getCreatives().get(position).getUiElement().getImage().getImageUrl())
                .into(holder.mImageView);
        holder.mCount.setText(countString(bean.getCreatives().get(position).getResources().get(0).getResourceExtInfo().getPlayCount()));
        holder.mTitle.setText(bean.getCreatives().get(position).getUiElement().getMainTitle().getTitle());
    }

    @Override
    public int getItemCount() {
        return bean.getCreatives().size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FrameLayout mFrameLayout;
        ImageView mImageView;
        TextView mCount;
        TextView mTitle;
        private OnItemClickListener listener;

        public InnerHolder(@NonNull View itemView, OnItemClickListener listener){
            super(itemView);
            this.listener = listener;
            mFrameLayout=itemView.findViewById(R.id.item_list_cover);
            mImageView=itemView.findViewById(R.id.item_list_cover_pic);
            mCount=itemView.findViewById(R.id.item_list_cover_count);
            mTitle=itemView.findViewById(R.id.item_list_cover_title);
            mFrameLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v,getAdapterPosition());
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
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
