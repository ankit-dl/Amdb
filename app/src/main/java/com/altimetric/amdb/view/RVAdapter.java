package com.altimetric.amdb.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.altimetric.amdb.R;
import com.altimetric.amdb.databinding.SearchResultItemBinding;
import com.altimetric.amdb.model.remote.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<SearchResult> searchResults = new ArrayList<>();

    SearchFragment.CheckBoxChanged listener;
    boolean isSearchFragment;

    public RVAdapter(SearchFragment.CheckBoxChanged listener, boolean isSearchFragment) {
        this.listener = listener;
        this.isSearchFragment = isSearchFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SearchResultItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.search_result_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(searchResults.get(position));
        if (isSearchFragment) {


            holder.searchResultItemBinding.addcart.setOnClickListener(v -> {

                listener.onCheckChanged(searchResults.get(position), holder.searchResultItemBinding.addcart.isChecked());
            });


        } else {
            holder.searchResultItemBinding.addcart.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return searchResults != null ? searchResults.size() : 0;
    }

    public void notifyData(List<SearchResult> updateData) {
        searchResults.clear();
        searchResults.addAll(updateData);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        SearchResultItemBinding searchResultItemBinding;

        ViewHolder(SearchResultItemBinding searchResultItemBinding) {
            super(searchResultItemBinding.getRoot());
            this.searchResultItemBinding = searchResultItemBinding;
        }

        void bindData(SearchResult resultModel) {
            searchResultItemBinding.setSearchResult(resultModel);
            searchResultItemBinding.executePendingBindings();

        }


    }

    public List<SearchResult> getData() {
        return searchResults;
    }
}
