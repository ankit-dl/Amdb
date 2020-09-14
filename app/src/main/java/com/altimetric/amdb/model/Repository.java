package com.altimetric.amdb.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.altimetric.amdb.model.remote.ApiService;
import com.altimetric.amdb.model.remote.BaseResult;
import com.altimetric.amdb.model.remote.SearchResult;
import com.altimetric.amdb.utils.EspressoIdlingResource;


import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

     ApiService service;
    private MutableLiveData<List<SearchResult>> resultMutableLiveData= new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingMutableLiveData= new MutableLiveData<>();;

    @Inject
    public Repository(ApiService service) {
        // service = RetrofitBuilder.getService(ApiService.class);
        this.service = service;

    }

    public LiveData<List<SearchResult>> getSearchResult(String searchQuery) {
        loadingMutableLiveData.setValue(true);
        EspressoIdlingResource.increment();
        service.getSearchResult(searchQuery).enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SearchResult> result = response.body().getResultModels();
                    Observable.fromIterable(result)
                            .filter(item -> !item.equals(this))

                            .toList()
                            .map(data -> {
                                Collections.sort(data);
                                return data;
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.computation())
                            .subscribe(new SingleObserver<List<SearchResult>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(List<SearchResult> searchResults) {

                                    resultMutableLiveData.setValue(searchResults);
                                    loadingMutableLiveData.setValue(false);
                                    EspressoIdlingResource.decrement();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    loadingMutableLiveData.setValue(false);
                                }
                            });

                }
            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {
                resultMutableLiveData.setValue(null);
                loadingMutableLiveData.setValue(false);
                EspressoIdlingResource.decrement();

            }
        });

        return resultMutableLiveData;
    }

    public LiveData<Boolean> getLoadingState() {

        return loadingMutableLiveData;
    }

    public LiveData<List<SearchResult>> getSearchLiveDate() {
        return resultMutableLiveData;
    }


}
