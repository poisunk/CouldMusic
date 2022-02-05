package com.example.couldmusic.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.BannerBean;
import com.example.couldmusic.bean.HomePageBean;
import com.example.couldmusic.bean.RecommendListBean;
import com.example.couldmusic.main.adapter.ListRecyclerAdapter;
import com.example.couldmusic.main.model.OnItemClickListener;
import com.example.couldmusic.playlist.ListFragment;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.example.couldmusic.widget.ListCover;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiscoverFragment extends Fragment implements View.OnClickListener{

    private final GlideImageLoader glideImageLoader=new GlideImageLoader();
    private final List<String> bannerImagePath=new ArrayList<>();

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;

    private Banner mBanner;



    public DiscoverFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_discover,container,false);
        initView(v);
        initEvent();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        load();
    }


    private void initView(View v){
        mSwipeRefresh=v.findViewById(R.id.swipe_discover);
        mBanner= v.findViewById(R.id.homepage_banner);
        mRecyclerView=v.findViewById(R.id.recommend_list_recycler);
    }

    private void initEvent(){
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });
    }


    private void load(){
        String address="http://redrock.udday.cn:2022/homepage/block/page";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final HomePageBean homePageBean= Utility.handleHomePageInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (homePageBean!=null){
                            loadBanner(homePageBean.getBannerBean());
                            loadRecommendList(homePageBean.getRecommendListBean());
                            mSwipeRefresh.setRefreshing(false);
                        }
                    }
                });
            }
        });

    }

    private void loadBanner(BannerBean bannerBean){
        bannerImagePath.clear();
        for(BannerBean.BannersBean bean : bannerBean.getBanners()){
            bannerImagePath.add(bean.getPic());
        }
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(glideImageLoader);
        mBanner.setDelayTime(3000);
        mBanner.isAutoPlay(true);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setImages(bannerImagePath);
        mBanner.start();
    }

    private void loadRecommendList(RecommendListBean recommendListBean){
        ListRecyclerAdapter adapter=new ListRecyclerAdapter(recommendListBean,this);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentManager manager= requireActivity().getSupportFragmentManager();
                Fragment fragment=manager.findFragmentById(R.id.fragment_play_list);
                if (fragment==null){
                    fragment=new ListFragment();
                }
                Bundle args=new Bundle();
                args.putSerializable("listbean",recommendListBean.getCreatives().get(position));
                fragment.setArguments(args);
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.add(R.id.included_interface,fragment);
                transaction.hide(manager.findFragmentById(R.id.included_interface));
                transaction.commit();
            }
        });
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(requireContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onClick(View v) {

    }


    private static class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
