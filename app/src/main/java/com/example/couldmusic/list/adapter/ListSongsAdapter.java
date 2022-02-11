package com.example.couldmusic.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.main.model.OnItemClickListener;

import java.util.List;

public class ListSongsAdapter extends RecyclerView.Adapter<ListSongsAdapter.InnerHolder>{

    private List<SongsDetailBean.Song> songs;
    private OnItemClickListener mClickListener;

    public ListSongsAdapter(List<SongsDetailBean.Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public ListSongsAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_play_list_song,
                parent, false),mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSongsAdapter.InnerHolder holder, int position) {

        holder.tvNum.setText(String.valueOf(position + 1));
        holder.tvName.setText(songs.get(position).getName());
        String ar = songs.get(position).getAl().getName() + "-";
        int num = songs.get(position).getAr().size() - 1;
        for (int i = 0; i < num; i++) {
            ar = ar + songs.get(position).getAr().get(i).getName() + ",";
        }
        ar = ar + songs.get(position).getAr().get(num).getName();
        holder.tvAr.setText(ar);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout linearLayout;
        TextView tvNum;
        TextView tvName;
        TextView tvAr;

        private OnItemClickListener mClickListener;

        public InnerHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            this.mClickListener=listener;
            linearLayout=itemView.findViewById(R.id.item_play_list_song);
            tvNum=itemView.findViewById(R.id.item_play_list_song_num);
            tvName=itemView.findViewById(R.id.item_play_list_song_name);
            tvAr=itemView.findViewById(R.id.item_play_list_song_ar);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v,getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
}
