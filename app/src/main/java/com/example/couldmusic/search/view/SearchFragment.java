package com.example.couldmusic.search.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.main.fragment.MainFragment;

public class SearchFragment extends Fragment implements View.OnClickListener {

    private static SearchFragment searchFragment=new SearchFragment();
    private String TAG="SearchFragment";

    private boolean isProgress=false;
    private Button bClose;
    private EditText etSearch;
    //判断搜索框的更改是否来自用户
    private boolean isFromUser=true;

    public static SearchFragment newInstance(){
        return searchFragment;
    }

    public static SearchFragment getInstance(){
        return searchFragment;
    }

    public SearchFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();

    }

    @Override
    public void onStop() {
        super.onStop();
        etSearch.setText("");
        FragmentManager manager=requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.remove(SearchSuggestFragment.getInstance());
        transaction.remove(SearchHotFragment.getInstance());
        transaction.remove(SearchResultFragment.getInstance());
        transaction.commit();
    }

    private void initView(View v){
        bClose=v.findViewById(R.id.fragment_search_close);
        etSearch=v.findViewById(R.id.fragment_search_edit_text);
        FragmentManager manager=requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.fragment_search_fragment,SearchSuggestFragment.getInstance());
        transaction.add(R.id.fragment_search_fragment,SearchHotFragment.getInstance());
        transaction.add(R.id.fragment_search_fragment,SearchResultFragment.getInstance());
        transaction.show(SearchHotFragment.getInstance())
                .hide(SearchSuggestFragment.getInstance())
                .hide(SearchResultFragment.getInstance())
                .commit();
    }

    private  void initEvent(){
        //设置搜索框的更改监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果不是来自用户则直接开始搜索，如果是则展示提示
                if(!isFromUser){
                    startSearch(s.toString());
                    isFromUser=true;
                }else {
                    showSuggest(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //用户键盘确定的监听
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                startSearch(v.getText().toString());
                return false;
            }
        });
        bClose.setOnClickListener(this);
    }

    /**
     * 如果搜索框有字符就提示，如果没有字符有展示热点搜索词
     * @param s
     */
    private void showSuggest(String s){
        if(s.equals("")&&!isProgress){
            FragmentManager manager=requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.show(SearchHotFragment.getInstance())
                    .hide(SearchSuggestFragment.getInstance())
                    .hide(SearchResultFragment.getInstance())
                    .commit();

        }else{
            FragmentManager manager=requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            SearchSuggestFragment.getInstance().loadSuggest(s);
            transaction.show(SearchSuggestFragment.getInstance())
                    .hide(SearchHotFragment.getInstance())
                    .hide(SearchResultFragment.getInstance())
                    .commit();
        }
    }


    @Override
    public void onClick(View v) {
        if(!isProgress){
            switch (v.getId()){
                case R.id.fragment_search_close:
                    FragmentManager manager=requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.remove(this).show(MainFragment.getInstance());
                    transaction.commit();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 开始搜索
     * @param s
     */
    private void startSearch(String s){
        if(!isProgress&& !s.equals("")){
            FragmentManager manager=requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.show(SearchResultFragment.newInstance(s))
                    .hide(SearchHotFragment.getInstance())
                    .hide(SearchSuggestFragment.getInstance())
                    .commit();
        }
    }

    /**
     * 设置搜索框中的信息，并直接开始搜索
     * @param s
     */
    public void setSearchText(String s){
        isFromUser=false;
        etSearch.setText(s);
    }

    public boolean isProgress() {
        return isProgress;
    }

    public void setProgress(boolean progress) {
        isProgress = progress;
    }

}
