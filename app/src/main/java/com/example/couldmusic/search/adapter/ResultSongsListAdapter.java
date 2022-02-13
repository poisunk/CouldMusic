package com.example.couldmusic.search.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SongsDetailBean;

import java.util.List;

public class ResultSongsListAdapter extends ArrayAdapter<SongsDetailBean.Song> {

    private int resource;
    private List<SongsDetailBean.Song> objects;

    public ResultSongsListAdapter(@NonNull Context context, int resource, @NonNull List<SongsDetailBean.Song> objects) {
        super(context, resource, objects);
        this.resource=resource;
        this.objects=objects;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SongsDetailBean.Song song=getItem(position);

        @SuppressLint("ViewHolder") View view = LayoutInflater.from (getContext ()).inflate (resource, parent, false);

        TextView tvName= view.findViewById(R.id.item_play_list_song_name);
        TextView tvAr= view.findViewById(R.id.item_play_list_song_ar);
        TextView tvNum=view.findViewById(R.id.item_play_list_song_num);

        tvName.setText(song.getName());
        String info=song.getArtists().get(0).getName();
        int size=song.getArtists().size();
        for(int i=1;i<size;i++){
            info=info+"/"+song.getArtists().get(i).getName();
        }
        tvAr.setText(info+song.getAlbum().getName());

        ViewGroup.LayoutParams params=tvNum.getLayoutParams();
        params.width=35;
        tvNum.setLayoutParams(params);

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
