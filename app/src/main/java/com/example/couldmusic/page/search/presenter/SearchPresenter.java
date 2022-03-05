package com.example.couldmusic.page.search.presenter;

import com.example.couldmusic.bean.SearchHotBean;
import com.example.couldmusic.bean.SearchPlaylistBean;
import com.example.couldmusic.bean.SearchSuggestBean;
import com.example.couldmusic.bean.SearchUsersBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.page.search.contract.SearchContract;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class SearchPresenter implements SearchContract.SearchPresenter , SearchContract.SearchModel.onFinishedListener {

    private SearchContract.SearchHotView searchHotView;
    private SearchContract.SearchSuggestView searchSuggestView;
    private SearchContract.SearchResultView searchResultView;

    private SearchContract.SearchView searchView;

    private SearchContract.SearchModel model;

    public SearchPresenter(SearchContract.SearchHotView searchHotView,SearchContract.SearchModel model){
        this.searchHotView=searchHotView;
        this.searchView=searchHotView;
        this.model=model;
    }

    public SearchPresenter(SearchContract.SearchSuggestView searchSuggestView,SearchContract.SearchModel model){
        this.searchSuggestView=searchSuggestView;
        this.searchView=searchSuggestView;
        this.model=model;
    }

    public SearchPresenter(SearchContract.SearchResultView searchResultView,SearchContract.SearchModel model){
        this.searchResultView=searchResultView;
        this.searchView=searchResultView;
        this.model=model;
    }

    @Override
    public void loadHotInfo() {
        String address="http://redrock.udday.cn:2022/search/hot";
        model.requestHotInfo(address,this);
    }

    @Override
    public void loadSuggests(String text) {
        String address = "http://redrock.udday.cn:2022/search/suggest?keywords=" + text + "&type=mobile";
        model.requestSuggests(address,this);
    }

    @Override
    public void loadResult(String keyWord) {
        String addressSongs="http://redrock.udday.cn:2022/search?keywords="+keyWord+"&type=1";
        model.requestSongsListResult(addressSongs,this);
        String addressPlaylist="http://redrock.udday.cn:2022/search?keywords="+keyWord+"&type=1000";
        model.requestPlayListResult(addressPlaylist,this);
        String addressUsers="http://redrock.udday.cn:2022/search?keywords="+keyWord+"&type=1002";
        model.requestUserListResult(addressUsers,this);

    }

    @Override
    public void finishHotInfo(SearchHotBean searchHotBean) {
        searchHotView.loadHotInfo(searchHotBean);
    }

    @Override
    public void finishSuggests(SearchSuggestBean searchSuggestBean) {
        searchSuggestView.loadSuggests(searchSuggestBean);

    }

    @Override
    public void finishPlayListResult(SearchPlaylistBean searchPlaylistBean) {
        searchResultView.loadResult(searchPlaylistBean);
    }

    @Override
    public void finishSongsListResult(SongsDetailBean songsDetailBean) {
        searchResultView.loadResult(songsDetailBean);
    }

    @Override
    public void finishUserListResult(SearchUsersBean searchUsersBean) {
        searchResultView.loadResult(searchUsersBean);
    }

    @Override
    public void onRequestFailed() {
        searchView.onRequestFailed();
    }
}
