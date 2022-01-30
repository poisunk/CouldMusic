package com.example.couldmusic.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.couldmusic.R;
import com.example.couldmusic.bean.BannerBean;
import com.example.couldmusic.bean.HomePageBean;
import com.example.couldmusic.bean.RecommendListBean;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.example.couldmusic.widget.ListCover;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiscoverFragment extends Fragment {

    private final GlideImageLoader glideImageLoader=new GlideImageLoader();

    private static HomePageBean homePageBean;
    private static List<String> bannerImagePath=new ArrayList<>();

    private final List<ListCover> lcItem=new ArrayList<>();

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
        ListCover lcItem1=v.findViewById(R.id.recommend_list_item_1);
        ListCover lcItem2=v.findViewById(R.id.recommend_list_item_2);
        ListCover lcItem3=v.findViewById(R.id.recommend_list_item_3);
        ListCover lcItem4=v.findViewById(R.id.recommend_list_item_4);
        ListCover lcItem5=v.findViewById(R.id.recommend_list_item_5);
        lcItem.add(lcItem1);
        lcItem.add(lcItem2);
        lcItem.add(lcItem3);
        lcItem.add(lcItem4);
        lcItem.add(lcItem5);
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
                final String responseText=response.body().string();
                final HomePageBean homePageBean= Utility.handleHomePageInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadBanner(homePageBean.getBannerBean());
                        loadRecommendList(homePageBean.getRecommendListBean());
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        });

    }

    private void loadBanner(BannerBean bannerBean){
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
        List<RecommendListBean.Creatives> creatives=recommendListBean.getCreatives();
        for(int i=0;i<lcItem.size();i++){
            lcItem.get(i).setImage(requireContext(),creatives.get(i).getUiElement().getImage().getImageUrl());
            lcItem.get(i).setTitle(creatives.get(i).getUiElement().getMainTitle().getTitle());
            lcItem.get(i).setPlayCount(creatives.get(i).getResources().get(0).getResourceExtInfo().getPlayCount());
        }
    }

    private static class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
