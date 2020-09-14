package com.altimetric.amdb.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.altimetric.amdb.model.Repository;
import com.altimetric.amdb.model.remote.SearchResult;
import com.altimetric.amdb.utils.Constants;


import java.util.ArrayList;
import java.util.Collections;

import java.util.List;


public class SearchViewModel extends ViewModel {
    Repository repository;
    private List<SearchResult> cartData = new ArrayList<>();

    public List<SearchResult> getCartData() {
        return cartData;
    }

    public void addCartItem(SearchResult item) {
        cartData.add(item);
    }

    public void removeCartItem(SearchResult item) {
        cartData.remove(item);
    }

    private MutableLiveData<List<SearchResult>> resultMutableLiveData;
    public int i = 0;

    public SearchViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<SearchResult>> getSearchResult(String searchQuery) {

        return repository.getSearchResult(searchQuery);
    }


    public void sortListBy(int sortBy) {
        resultMutableLiveData = (MutableLiveData<List<SearchResult>>) repository.getSearchLiveDate();
        if (resultMutableLiveData == null) return;
        List<SearchResult> data = resultMutableLiveData.getValue();
        if (data == null) return;
        Log.i("sort data key", sortBy + "");
        Collections.sort(data, (searchResult, t1) -> {

            switch (sortBy) {
                case 2:
                    if (searchResult.getArtistName() == null || t1.getArtistName() == null)
                        return 0;
                    return -searchResult.getArtistName().compareTo(t1.getArtistName());
                case 0:
                    if (searchResult.getCollectionName() == null || t1.getCollectionName() == null)
                        return 0;
                    return -searchResult.getCollectionName().compareTo(t1.getCollectionName());
                case 1:
                    if (searchResult.getTrackName() == null || t1.getTrackName() == null) return 0;
                    return -searchResult.getTrackName().compareTo(t1.getTrackName());
                case 3:
                    if (searchResult.getCollectionPrice() == null || t1.getCollectionPrice() == null)
                        return 0;
                    return -searchResult.getCollectionPrice().compareTo(t1.getCollectionPrice());
                default:
                    return 0;
            }

        });

        // resultMutableLiveData = (MutableLiveData<List<SearchResult>>) repository.getSearchLiveDate();
        resultMutableLiveData.setValue(data);

    }

    public LiveData<Boolean> getLoadingState() {

        return repository.getLoadingState();
    }

    public LiveData<List<SearchResult>> getSearchLiveDate() {
        return repository.getSearchLiveDate();
    }

    public void addToCart(SearchResult result) {
        {
            System.out.println(result);
        }


    }
}

