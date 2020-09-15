package com.altimetric.amdb.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.altimetric.amdb.model.remote.ApiService;
import com.altimetric.amdb.model.remote.BaseResult;
import com.altimetric.amdb.model.remote.SearchResult;
import com.altimetric.amdb.utils.EspressoIdlingResource;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;


import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    ApiService service;
    private MutableLiveData<List<SearchResult>> resultMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingMutableLiveData = new MutableLiveData<>();
    Disposable disposable;

    @Inject
    public Repository(ApiService service) {
        // service = RetrofitBuilder.getService(ApiService.class);
        this.service = service;

    }

    public LiveData<List<SearchResult>> getSearchResult(String searchQuery) {
        loadingMutableLiveData.setValue(true);
        EspressoIdlingResource.increment();


        service.getSearchResult(searchQuery)
                .map(BaseResult::getResultModels)
                .flatMap(Observable::fromIterable)
                .distinct()
                .sorted()
                .toList()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<SearchResult> searchResults) {
                        Log.e("result size", searchResults.size() + "");
                        resultMutableLiveData.setValue(searchResults);
                        loadingMutableLiveData.setValue(false);
                        EspressoIdlingResource.decrement();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
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

    public void clean() {
        if (disposable != null)
            disposable.dispose();
    }
}
