package com.example.couldmusic.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    //确定搜索的关键词
    private String searchText;

    private String[] tabStr={"单曲","歌单","用户"};
    private List<Fragment> fragments=new ArrayList<>(4);

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
                load();
            }
        }
    }

    private void initView(View v){
        mTabLayout=v.findViewById(R.id.fragment_search_result_tab_layout);
        mViewPager2=v.findViewById(R.id.fragment_search_result_view_paper);
        mProgressBar=v.findViewById(R.id.fragment_search_result_progress_bar);
    }

    private void load(){
        fragments.clear();
        mViewPager2.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        loadSongs();
    }

    /**
     * 因为种种原因，按顺序请求才是最好的方法
     * 加载歌曲
     */
    private void loadSongs(){
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
                        ResultSongsListFragment fragment=ResultSongsListFragment.newInstance(songsDetailBean);
                        fragments.add(fragment);
                        loadLists();
                    }
                });
            }
        });
    }

    /**
     * 加载歌单
     */
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
                        ResultPlayListFragment fragment=ResultPlayListFragment.newInstance(adapter);
                        fragments.add(1,fragment);
                        loadUsers();
                    }
                });
            }
        });

    }

    /**
     * 加载用户
     */
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
                        ResultUserListFragment fragment=ResultUserListFragment.newInstance(adapter);
                        fragments.add(2,fragment);
                        show();
                    }
                });
            }
        });
    }

    /**
     * 展示信息
     */
    private void show(){
        SearchFragment.getInstance().setProgress(false);
        mViewPager2.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        SearchResultPaperAdapter adapter=new SearchResultPaperAdapter(requireActivity(),fragments);
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
