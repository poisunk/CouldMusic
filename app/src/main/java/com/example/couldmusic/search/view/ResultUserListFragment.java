package com.example.couldmusic.search.view;

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

import com.example.couldmusic.R;

import com.example.couldmusic.search.adapter.ResultUserListAdapter;

public class ResultUserListFragment extends Fragment {

    private static ResultUserListFragment resultUserListFragment=new ResultUserListFragment();

    private ResultUserListAdapter adapter;

    private ListView listView;
    private TextView emptyText;

    public static ResultUserListFragment newInstance(ResultUserListAdapter adapter) {
        resultUserListFragment=new ResultUserListFragment();
        resultUserListFragment.setAdapter(adapter);
        return resultUserListFragment;
    }

    public static ResultUserListFragment getInstance(){
        return resultUserListFragment;
    }


    public ResultUserListFragment(){

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void setAdapter(ResultUserListAdapter adapter) {
        this.adapter = adapter;
    }
}
