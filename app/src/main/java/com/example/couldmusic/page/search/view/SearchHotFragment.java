package com.example.couldmusic.page.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SearchHotBean;
import com.example.couldmusic.page.main.model.OnItemClickListener;
import com.example.couldmusic.page.search.adapter.HotListRecyclerAdapter;
import com.example.couldmusic.page.search.contract.SearchContract;
import com.example.couldmusic.page.search.model.SearchModel;
import com.example.couldmusic.page.search.presenter.SearchPresenter;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchHotFragment extends Fragment implements View.OnClickListener, SearchContract.SearchHotView {

    private static SearchHotFragment searchHotFragment=new SearchHotFragment();


    private boolean isProgress=false;


    private RecyclerView mRecycler;

    private SearchPresenter presenter;

    public static SearchHotFragment newInstance(){
        return searchHotFragment;
    }

    public static SearchHotFragment getInstance(){
        return searchHotFragment;
    }

    public SearchHotFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new SearchPresenter(this,new SearchModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_hot,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        presenter.loadHotInfo();
    }

    private void initView(View v){
        mRecycler=v.findViewById(R.id.fragment_search_hot_recycler);

    }

    private void initEvent(){

    }

    @Override
    public void loadHotInfo(SearchHotBean searchHotBean) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HotListRecyclerAdapter adapter=new HotListRecyclerAdapter(searchHotBean.getHots());
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        SearchFragment.getInstance().setSearchText(searchHotBean.getHots().get(position).getFirst());
                    }
                });
                mRecycler.setAdapter(adapter);
                mRecycler.setLayoutManager(new GridLayoutManager(requireContext(),2));
            }
        });

    }

    @Override
    public void onRequestFailed() {
        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(!isProgress){
            switch (v.getId()){

            }
        }
    }
}
