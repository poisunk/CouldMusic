package com.example.couldmusic.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.couldmusic.R;

public class SearchResultFragment extends Fragment {

    private static SearchResultFragment searchResultFragment=new SearchResultFragment();

    private String searchText;

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

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
