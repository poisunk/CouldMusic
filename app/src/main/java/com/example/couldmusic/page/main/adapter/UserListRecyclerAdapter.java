package com.example.couldmusic.page.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.PlayListDetailBean;
import com.example.couldmusic.page.main.model.OnItemClickListener;

import java.util.List;

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.InnerHolder> {

    private List<PlayListDetailBean> list;
    private Fragment fragment;
    private OnItemClickListener mClickListener;

    public UserListRecyclerAdapter(List<PlayListDetailBean> list, Fragment fragment){
        this.list=list;
        this.fragment=fragment;
    }
    @NonNull
    @Override
    public UserListRecyclerAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,
                parent, false),mClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.tvListInfo.setText(list.get(position).getTrackCount()+" 首");
        holder.tvListName.setText(list.get(position).getName());
        Glide.with(fragment).load(list.get(position).getCoverImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout linearLayout;

        private ImageView imageView;
        //名字
        private TextView tvListName;
        //歌单信息
        private TextView tvListInfo;

        private OnItemClickListener listener;


        public InnerHolder(@NonNull View itemView, OnItemClickListener listener){
            super(itemView);
            this.listener=listener;
            linearLayout=itemView.findViewById(R.id.item_list);
            imageView=itemView.findViewById(R.id.item_list_cover);
            tvListInfo=itemView.findViewById(R.id.item_list_info);
            tvListName=itemView.findViewById(R.id.item_list_name);
            linearLayout.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            listener.onItemClick(v,getAdapterPosition());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
}
