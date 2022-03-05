package com.example.couldmusic.page.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.SearchSuggestBean;
import com.example.couldmusic.page.search.contract.SearchContract;
import com.example.couldmusic.page.search.model.SearchModel;
import com.example.couldmusic.page.search.presenter.SearchPresenter;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchSuggestFragment extends Fragment implements SearchContract.SearchSuggestView {


    private static SearchSuggestFragment searchSuggestFragment=new SearchSuggestFragment();
    private static boolean isProgress=false;

    private String keyWord;

    private ListView mListView;

    private SearchPresenter presenter;

    public static SearchSuggestFragment newInstance(String keyWord){
        searchSuggestFragment.setKeyWord(keyWord);
        return searchSuggestFragment;
    }

    public static SearchSuggestFragment getInstance(){
        return searchSuggestFragment;
    }

    public SearchSuggestFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new SearchPresenter(this,new SearchModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_suggest,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initView(View v){
        mListView=v.findViewById(R.id.fragment_search_suggest_list);
    }

    private void initEvent(){
    }

    /**
     * 加载提示信息
     * @param keyWord
     */
    public void loadSuggests(String keyWord) {
        presenter.loadSuggests(keyWord);
    }


    public void setKeyWord (String keyWord){
            this.keyWord = keyWord;
    }

    @Override
    public void onRequestFailed() {
        Toast.makeText(requireContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadSuggests(SearchSuggestBean searchSuggestBean) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(searchSuggestBean.getResult()!=null
                        &&searchSuggestBean.getResult().getAllMatch()!=null) {
                    List<Map<String, String>> mListItems = new ArrayList<>();
                    for (SearchSuggestBean.Result.AllMatch allMatch : searchSuggestBean.getResult().getAllMatch()) {
                        Map<String, String> map = new HashMap<>();
                        map.put("suggest", allMatch.getKeyword());
                        mListItems.add(map);
                    }
                    SimpleAdapter adapter = new SimpleAdapter(requireContext(), mListItems, R.layout.item_search_suggest
                            , new String[]{"suggest"}, new int[]{R.id.item_search_suggest_text});
                    mListView.setAdapter(adapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SearchFragment.getInstance().setSearchText(searchSuggestBean.getResult()
                                    .getAllMatch().get(position).getKeyword());
                        }
                    });
                }else{
                    mListView.setAdapter(null);
                }
                isProgress=false;

            }
        });

    }
}
