package com.altimetric.amdb.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.altimetric.amdb.App;
import com.altimetric.amdb.R;
import com.altimetric.amdb.databinding.ActivityMainBinding;
import com.altimetric.amdb.databinding.FragmentSearchBinding;
import com.altimetric.amdb.model.remote.SearchResult;
import com.altimetric.amdb.utils.SearchQuery;
import com.altimetric.amdb.viewmodel.SearchViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.altimetric.amdb.utils.Constants.hideKeyboard;


public class SearchFragment extends Fragment {
    @Inject
    SearchViewModel searchViewModel;
    FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.mainResultRecyclerView.setAdapter(new RVAdapter((item, state) -> {

            if (state) searchViewModel.addCartItem(item);
            else searchViewModel.removeCartItem(item);
        }, true));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getInstance().getComponent().inject(this);
        binding.setViewModel(searchViewModel);
        binding.setSearchQuery(new SearchQuery());
        binding.setHandler(new Handler(getActivity()));

        searchViewModel.getLoadingState().observe(getViewLifecycleOwner(), aBoolean -> {
            binding.mainProgressBar.setVisibility(aBoolean ? View.VISIBLE : View.INVISIBLE);
        });

        searchViewModel.getSearchLiveDate().observe(getViewLifecycleOwner(), searchResults -> {

            if (binding.mainResultRecyclerView.getAdapter() instanceof RVAdapter) {
                ((RVAdapter) binding.mainResultRecyclerView.getAdapter()).notifyData(searchResults);
            }
        });
    }

    public static class Handler {

        Context context;

        public Handler(Context context) {
            this.context = context;
        }

        public void onClick(View v) {
            if (context instanceof AppCompatActivity) {

                hideKeyboard(context);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.container, new CartFragment()).addToBackStack("").commit();
            }
        }
    }

    public interface CheckBoxChanged {
        void onCheckChanged(SearchResult item, boolean state);
    }


}