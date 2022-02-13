package com.example.couldmusic.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.couldmusic.R;
import com.example.couldmusic.main.model.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class SearchResultPaperAdapter extends RecyclerView.Adapter<SearchResultPaperAdapter.InnerHolder> {


    private List<ArrayAdapter> list;

    public SearchResultPaperAdapter(List<ArrayAdapter> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public SearchResultPaperAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result_list,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.listView.setEmptyView(holder.textView);
        holder.listView.setAdapter(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {

        private ListView listView;
        private TextView textView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            listView= itemView.findViewById(R.id.item_search_result_list);
            textView= itemView.findViewById(R.id.item_search_result_null);
        }
    }


}
