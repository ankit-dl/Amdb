package com.altimetric.amdb.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.altimetric.amdb.App;
import com.altimetric.amdb.R;
import com.altimetric.amdb.databinding.FragmentCartBinding;
import com.altimetric.amdb.viewmodel.SearchViewModel;

import javax.inject.Inject;


public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    @Inject
    SearchViewModel searchViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        App.getInstance().getComponent().inject(this);

        RVAdapter adapter = new RVAdapter(null, false);
        binding.cartRV.setAdapter(adapter);
        adapter.notifyData(searchViewModel.getCartData());


    }
}