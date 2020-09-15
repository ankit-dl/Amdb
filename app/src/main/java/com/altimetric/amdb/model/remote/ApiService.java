package com.altimetric.amdb.model.remote;




import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Observable<BaseResult> getSearchResult(@Query("term") String query);
}
