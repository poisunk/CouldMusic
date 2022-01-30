package com.example.couldmusic.bean;

public class HomePageBean {

    public BannerBean bannerBean;

    public RecommendListBean recommendListBean;

    public BannerBean getBannerBean() {
        return bannerBean;
    }

    public void setBannerBean(BannerBean bannerBean) {
        this.bannerBean = bannerBean;
    }

    public RecommendListBean getRecommendListBean() {
        return recommendListBean;
    }

    public void setRecommendListBean(RecommendListBean recommendListBean) {
        this.recommendListBean = recommendListBean;
    }
}
