package com.altimetric.amdb.model.remote;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.altimetric.amdb.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class SearchResult extends BaseObservable implements Comparable<SearchResult> {

    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    private boolean isChecked;

    @Bindable
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyPropertyChanged(BR.checked);
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }


    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(trackName, that.trackName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackName);
    }

    @Override
    public String toString() {
        return
                "trackName= " + trackName;
    }

    @Override
    public int compareTo(SearchResult searchResult) {
        String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
        DateFormat f = new SimpleDateFormat(DATE_FORMAT_PATTERN);

        try {
            // Date
            // searchResult.getReleaseDate().substring(0,searchResult.getReleaseDate().length()-2);
            return f.parse(searchResult.getReleaseDate().replace('Z', ' ').trim()).compareTo(f.parse(this.getReleaseDate().replace('Z', ' ').trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // return searchResult.getReleaseDate()-this.getReleaseDate();
        return -1;
    }
}
