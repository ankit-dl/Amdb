package com.altimetric.amdb.utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.recyclerview.widget.RecyclerView;

import com.altimetric.amdb.model.remote.SearchResult;
import com.altimetric.amdb.view.RVAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BindableAdapter {
    @BindingAdapter("setUrl")
    public static void setImageUrl(ImageView imageView, String url) {


        Picasso.get()
                .load(url)
                .resize(150, 200)
                .into(imageView);

    }


}
