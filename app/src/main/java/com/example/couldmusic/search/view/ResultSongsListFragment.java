package com.example.couldmusic.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.list.view.ListFragment;
import com.example.couldmusic.music.MusicFragment;
import com.example.couldmusic.search.adapter.ResultPlaylistListAdapter;
import com.example.couldmusic.search.adapter.ResultSongsListAdapter;

import java.util.List;

public class ResultSongsListFragment extends Fragment {

    private static ResultSongsListFragment resultSongsListFragment=new ResultSongsListFragment();

    private SongsDetailBean songsDetailBean;

    private ListView listView;
    private TextView emptyText;

    public static ResultSongsListFragment newInstance(SongsDetailBean songsDetailBean) {
        resultSongsListFragment=new ResultSongsListFragment();
        resultSongsListFragment.setBean(songsDetailBean);
        return resultSongsListFragment;
    }

    public static ResultSongsListFragment getInstance(){
        return resultSongsListFragment;
    }


    public ResultSongsListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_search_result_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View v){
        listView=v.findViewById(R.id.item_search_result_list);
        emptyText=v.findViewById(R.id.item_search_result_null);

        ResultSongsListAdapter adapter;
        if (songsDetailBean != null&&songsDetailBean.getSongs()!=null) {
            adapter = new ResultSongsListAdapter(getInstance().requireContext(),
                    R.layout.item_play_list_song, songsDetailBean.getSongs());
        }else{
            adapter = new ResultSongsListAdapter(getInstance().requireContext(),
                    R.layout.item_play_list_song, null);
        }
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyText);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<SongsDetailBean.Song> songs=songsDetailBean.getSongs();
                FragmentManager manager=requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                MusicFragment fragment= MusicFragment.newInstance(songs
                        ,position,SearchFragment.getInstance());
                transaction.hide(SearchFragment.getInstance())
                        .show(fragment)
                        .commit();
            }
        });
    }

    public void setBean(SongsDetailBean bean) {
        this.songsDetailBean = bean;
    }
}
