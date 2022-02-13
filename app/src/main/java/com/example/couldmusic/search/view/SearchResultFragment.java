package com.example.couldmusic.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SearchPlaylistBean;
import com.example.couldmusic.bean.SearchUsersBean;
import com.example.couldmusic.bean.SongsDetailBean;
import com.example.couldmusic.bean.UserPlayListBean;
import com.example.couldmusic.search.adapter.ResultPlaylistListAdapter;
import com.example.couldmusic.search.adapter.ResultSongsListAdapter;
import com.example.couldmusic.search.adapter.ResultUserListAdapter;
import com.example.couldmusic.search.adapter.SearchResultPaperAdapter;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultFragment extends Fragment {

    private static SearchResultFragment searchResultFragment=new SearchResultFragment();

    private static String TAG="SearchResult";

    private String searchText;

    private String[] tabStr={"单曲","歌单","用户"};
    private List<ArrayAdapter> adapters=new ArrayList<>();

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private ProgressBar mProgressBar;

    public static SearchResultFragment newInstance(String s){
        searchResultFragment.setSearchText(s);
        return searchResultFragment;
    }

    public static SearchResultFragment getInstance(){
        return searchResultFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(searchText!=null) {
                loadSongs();
            }
        }
    }

    private void initView(View v){
        mTabLayout=v.findViewById(R.id.fragment_search_result_tab_layout);
        mViewPager2=v.findViewById(R.id.fragment_search_result_view_paper);
        mProgressBar=v.findViewById(R.id.fragment_search_result_progress_bar);
    }

    private void loadSongs(){
        adapters.clear();
        mViewPager2.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        SearchFragment.getInstance().setProgress(true);
        String address="http://redrock.udday.cn:2022/search?keywords="+searchText+"&type=1";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                        SearchFragment.getInstance().setProgress(false);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SongsDetailBean songsDetailBean= Utility.handleSearchSongsDetailInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ResultSongsListAdapter adapter;
                        if (songsDetailBean != null&&songsDetailBean.getSongs()!=null) {
                             adapter = new ResultSongsListAdapter(requireContext(),
                                    R.layout.item_play_list_song, songsDetailBean.getSongs());
                        }else{
                            adapter = new ResultSongsListAdapter(requireContext(),
                                    R.layout.item_play_list_song, null);
                        }
                        adapters.add(adapter);
                        loadLists();
                    }
                });
            }
        });
    }

    private void loadLists(){
        String address="http://redrock.udday.cn:2022/search?keywords="+searchText+"&type=1000";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                        SearchFragment.getInstance().setProgress(false);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SearchPlaylistBean searchPlaylistBean=Utility.handleSearchPlayListInfo(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ResultPlaylistListAdapter adapter;
                        if(searchPlaylistBean!=null&&searchPlaylistBean.getResult()!=null){
                            adapter=new ResultPlaylistListAdapter(requireContext(),
                                    R.layout.item_list,searchPlaylistBean.getResult().getPlaylists());
                        }else{
                            adapter=new ResultPlaylistListAdapter(requireContext(),
                                    R.layout.item_list,null);
                        }
                        adapters.add(adapter);
                        loadUsers();
                    }
                });
            }
        });

    }

    private void loadUsers(){
        String address="http://redrock.udday.cn:2022/search?keywords="+searchText+"&type=1002";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                        SearchFragment.getInstance().setProgress(false);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText= Objects.requireNonNull(response.body()).string();
                final SearchUsersBean searchUsersBean=Utility.handleSearchUsersBean(responseText);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ResultUserListAdapter adapter;
                        if(searchUsersBean!=null&&searchUsersBean.getResult().getUserprofiles()!=null){
                            adapter=new ResultUserListAdapter(requireContext(),
                                    R.layout.item_user_list,searchUsersBean.getResult().getUserprofiles());
                        }else{
                            adapter=new ResultUserListAdapter(requireContext(),
                                    R.layout.item_user_list,null);
                        }
                        adapters.add(adapter);
                        show();
                        SearchFragment.getInstance().setProgress(false);
                        mProgressBar.setVisibility(View.GONE);
                        mViewPager2.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void show(){
        SearchResultPaperAdapter adapter=new SearchResultPaperAdapter(adapters);
        mViewPager2.setAdapter(adapter);
        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabStr[position]);
            }
        }).attach();
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
