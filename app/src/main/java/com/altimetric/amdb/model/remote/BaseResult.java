package com.altimetric.amdb.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResult {
    @SerializedName("resultCount")
    @Expose
    private int resultCount;
    @SerializedName("results")
    @Expose
    private List<SearchResult> resultModels = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<SearchResult> getResultModels() {
        return resultModels;
    }

    public void setResultModels(List<SearchResult> resultModels) {
        this.resultModels = resultModels;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "resultCount=" + resultCount +
                ", resultModels=" + resultModels +
                '}';
    }
}
