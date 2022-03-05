package com.example.couldmusic.page.search.view;

import android.os.Bundle;
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
import com.example.couldmusic.page.list.view.ListFragment;
import com.example.couldmusic.page.search.adapter.ResultPlaylistListAdapter;

public class ResultPlayListFragment extends Fragment {

    private static ResultPlayListFragment resultPlayListFragment=new ResultPlayListFragment();

    private ResultPlaylistListAdapter adapter;

    private ListView listView;
    private TextView emptyText;

    public static ResultPlayListFragment newInstance(ResultPlaylistListAdapter adapter) {
       resultPlayListFragment=new ResultPlayListFragment();
       resultPlayListFragment.setAdapter(adapter);
       return resultPlayListFragment;
    }

    public static ResultPlayListFragment getInstance(){
        return resultPlayListFragment;
    }


    public ResultPlayListFragment(){

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

        listView.setAdapter(adapter);
        listView.setEmptyView(emptyText);

        //设置list中item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long ID = adapter.getItem(position).getId();
                FragmentManager manager=requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                ListFragment fragment=ListFragment.newInstance(String.valueOf(ID));
                transaction.add(R.id.included_interface,fragment,"ListFragment")
                        .addToBackStack("ListFragment")
                        .commit();
            }
        });
    }

    public void setAdapter(ResultPlaylistListAdapter adapter) {
        this.adapter = adapter;
    }
}
