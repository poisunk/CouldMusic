package com.example.couldmusic.page.search.view;

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
import com.example.couldmusic.page.search.adapter.ResultPlaylistListAdapter;
import com.example.couldmusic.page.search.adapter.ResultUserListAdapter;
import com.example.couldmusic.page.search.adapter.SearchResultPaperAdapter;
import com.example.couldmusic.page.search.contract.SearchContract;
import com.example.couldmusic.page.search.model.SearchModel;
import com.example.couldmusic.page.search.presenter.SearchPresenter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment implements SearchContract.SearchResultView {

    private static SearchResultFragment searchResultFragment=new SearchResultFragment();

    private static String TAG="SearchResult";

    //确定搜索的关键词
    private String searchText;

    private String[] tabStr={"单曲","歌单","用户"};
    private List<Fragment> fragments=new ArrayList<>(4);

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private ProgressBar mProgressBar;

    private SearchPresenter presenter;


    public static SearchResultFragment newInstance(String s){
        searchResultFragment.setSearchText(s);
        return searchResultFragment;
    }

    public static SearchResultFragment getInstance(){
        return searchResultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new SearchPresenter(this,new SearchModel());
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
                fragments.clear();
                fragments.add(new Fragment());
                fragments.add(new Fragment());
                fragments.add(new Fragment());
                mViewPager2.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                SearchFragment.getInstance().setProgress(true);
                presenter.loadResult(searchText);
            }
        }
    }

    private void initView(View v){
        mTabLayout=v.findViewById(R.id.fragment_search_result_tab_layout);
        mViewPager2=v.findViewById(R.id.fragment_search_result_view_paper);
        mProgressBar=v.findViewById(R.id.fragment_search_result_progress_bar);
    }

    /**
     * 加载歌曲
     */
    private void loadSongs(SongsDetailBean songsDetailBean){
        ResultSongsListFragment fragment=ResultSongsListFragment.newInstance(songsDetailBean);
        fragments.set(0,fragment);
    }

    /**
     * 加载歌单
     */
    private void loadLists(SearchPlaylistBean searchPlaylistBean){

        ResultPlaylistListAdapter adapter;
        if(searchPlaylistBean!=null&&searchPlaylistBean.getResult()!=null){
            adapter=new ResultPlaylistListAdapter(requireContext(),
                    R.layout.item_list,searchPlaylistBean.getResult().getPlaylists());
        }else{
            adapter=new ResultPlaylistListAdapter(requireContext(),
                    R.layout.item_list,null);
        }
        ResultPlayListFragment fragment=ResultPlayListFragment.newInstance(adapter);
        fragments.set(1,fragment);
    }

    /**
     * 加载用户
     */
    private void loadUsers(SearchUsersBean searchUsersBean){

        ResultUserListAdapter adapter;
        if(searchUsersBean!=null&&searchUsersBean.getResult().getUserprofiles()!=null){
            adapter=new ResultUserListAdapter(requireContext(),
                    R.layout.item_user_list,searchUsersBean.getResult().getUserprofiles());
        }else{
            adapter=new ResultUserListAdapter(requireContext(),
                    R.layout.item_user_list,null);
        }
        ResultUserListFragment fragment=ResultUserListFragment.newInstance(adapter);
        fragments.set(2,fragment);
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

    @Override
    public void onRequestFailed() {
        Toast.makeText(requireContext(),"网络请求失败!",Toast.LENGTH_SHORT).show();
    }

    private int completeCount=0;
    private final int SEARCH_COMPLETE_NUMBER=3;

    @Override
    public void loadResult(Object object) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(object instanceof SongsDetailBean){
                    loadSongs((SongsDetailBean) object);
                    completeCount++;
                }else if(object instanceof SearchPlaylistBean){
                    loadLists((SearchPlaylistBean) object);
                    completeCount++;
                }else if(object instanceof  SearchUsersBean){
                    loadUsers((SearchUsersBean) object);
                    completeCount++;
                }

                if(completeCount==SEARCH_COMPLETE_NUMBER){
                    completeCount=0;
                    show();
                }
            }
        });
    }
}
