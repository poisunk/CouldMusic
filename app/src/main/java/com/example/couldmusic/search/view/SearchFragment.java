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
        transaction.commit();
    }

    private void initView(View v){
        bClose=v.findViewById(R.id.fragment_search_close);
        etSearch=v.findViewById(R.id.fragment_search_edit_text);
        FragmentManager manager=requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.fragment_search_fragment,SearchSuggestFragment.getInstance());
        transaction.add(R.id.fragment_search_fragment,SearchHotFragment.getInstance());
        transaction.show(SearchHotFragment.getInstance())
                .hide(SearchSuggestFragment.getInstance())
                .commit();
    }

    private  void initEvent(){
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG,s.toString());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG,s.toString());
                showSuggest(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG,"after");
                showSuggest(s.toString());
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                startSearch(v.getText().toString());
                return false;
            }
        });
        bClose.setOnClickListener(this);
    }

    private void showSuggest(String s){
        if(s.equals("")){
            FragmentManager manager=requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.hide(SearchSuggestFragment.getInstance());
            transaction.show(SearchHotFragment.getInstance());
            transaction.commit();
        }else{
            FragmentManager manager=requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            SearchSuggestFragment.getInstance().loadSuggest(s);
            transaction.show(SearchSuggestFragment.getInstance());
            transaction.hide(SearchHotFragment.getInstance());
            transaction.commit();
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

    private void startSearch(String s){
        if(!isProgress){
            Toast.makeText(requireContext(),s,Toast.LENGTH_SHORT).show();
        }
    }

    public void setSearchText(String s){
        etSearch.setText(s);
        startSearch(s);
    }

    public boolean isProgress() {
        return isProgress;
    }

    public void setProgress(boolean progress) {
        isProgress = progress;
    }

}
