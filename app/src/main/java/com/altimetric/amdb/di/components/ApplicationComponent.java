package com.altimetric.amdb.di.components;

import android.app.Application;

import com.altimetric.amdb.di.modules.ApiModule;
import com.altimetric.amdb.di.modules.SearchViewModelModule;
import com.altimetric.amdb.view.CartFragment;
import com.altimetric.amdb.view.SearchActivity;
import com.altimetric.amdb.view.SearchFragment;
import com.altimetric.amdb.viewmodel.SearchViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules = {ApiModule.class, SearchViewModelModule.class})
public interface ApplicationComponent {

   SearchViewModel getSearchViewModel();
    void inject (SearchFragment searchFragment);
    void inject (CartFragment cartFragment);
    void inject (SearchActivity activity);


    @Component.Factory
    public interface Builder {

        ApplicationComponent build(@BindsInstance @Singleton Application application);

    }
}
