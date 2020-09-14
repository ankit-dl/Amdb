package com.altimetric.amdb.model.remote;

import android.database.Observable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
     Call<BaseResult> getSearchResult(@Query("term") String query);
}
