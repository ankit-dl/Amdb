package com.altimetric.amdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.altimetric.amdb.App;
import com.altimetric.amdb.R;
import com.altimetric.amdb.databinding.ActivityMainBinding;
import com.altimetric.amdb.model.remote.SearchResult;
import com.altimetric.amdb.utils.Constants;
import com.altimetric.amdb.utils.SearchQuery;
import com.altimetric.amdb.viewmodel.SearchViewModel;
import com.altimetric.amdb.viewmodel.SearchViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity {
    @Inject
    SearchViewModel viewModel;

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        App.getInstance().getComponent().inject(this);


        getSupportFragmentManager().beginTransaction().add(R.id.container, new SearchFragment()).commit();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.clean();
    }
}