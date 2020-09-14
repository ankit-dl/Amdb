package com.altimetric.amdb.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.altimetric.amdb.model.Repository;

public class SearchViewModelFactory implements ViewModelProvider.Factory {
    Repository repository;
    public SearchViewModelFactory( Repository repository) {


        this.repository=repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SearchViewModel(repository);
    }
}
