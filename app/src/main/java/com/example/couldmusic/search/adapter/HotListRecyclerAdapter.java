package com.example.couldmusic.search.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SearchHotBean;
import com.example.couldmusic.main.model.OnItemClickListener;

import java.util.List;

public class HotListRecyclerAdapter extends RecyclerView.Adapter<HotListRecyclerAdapter.InnerHolder> {

    private List<SearchHotBean.Hot> hots;
    private OnItemClickListener mClickListener;

    public HotListRecyclerAdapter(List<SearchHotBean.Hot> hots){
        this.hots=hots;
    }

    @NonNull
    @Override
    public HotListRecyclerAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_hot,
                parent, false),mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.tvName.setText(hots.get(position).getFirst());
        holder.tvNum.setText(String.valueOf(position+1));
        if(position<3){
            holder.tvNum.setTextColor(Color.RED);
            holder.tvName.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return hots.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvNum;
        private TextView tvName;
        private OnItemClickListener mClickListener;

        public InnerHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            mClickListener=listener;
            tvName=itemView.findViewById(R.id.item_search_hot_name);
            tvNum=itemView.findViewById(R.id.item_search_hot_num);
            tvName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v,getPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
}
