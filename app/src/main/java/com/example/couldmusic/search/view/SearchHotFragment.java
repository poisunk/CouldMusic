package com.example.couldmusic.search.view;

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
import com.example.couldmusic.main.model.OnItemClickListener;
import com.example.couldmusic.search.adapter.HotListRecyclerAdapter;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchHotFragment extends Fragment implements View.OnClickListener {

    private static SearchHotFragment searchHotFragment=new SearchHotFragment();


    private boolean isProgress=false;


    private RecyclerView mRecycler;

    public static SearchHotFragment newInstance(){
        return searchHotFragment;
    }

    public static SearchHotFragment getInstance(){
        return searchHotFragment;
    }

    public SearchHotFragment(){

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
        load();
    }

    private void initView(View v){
        mRecycler=v.findViewById(R.id.fragment_search_hot_recycler);

    }

    private  void initEvent(){

    }

    private void load(){
        loadHotInfo();
    }

    /**
     * 加载热点信息
     */
    private void loadHotInfo(){
        isProgress=true;
        String address="http://redrock.udday.cn:2022/search/hot";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SearchHotBean searchHotBean= Utility.handleSearchHotInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isProgress=false;
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
        });
    }

    @Override
    public void onClick(View v) {
        if(!isProgress){
            switch (v.getId()){

            }
        }
    }
}
