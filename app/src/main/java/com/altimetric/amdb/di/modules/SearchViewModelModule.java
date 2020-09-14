package com.altimetric.amdb.di.modules;



import com.altimetric.amdb.model.Repository;
import com.altimetric.amdb.viewmodel.SearchViewModel;
import com.altimetric.amdb.viewmodel.SearchViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchViewModelModule {
    @Provides
    @Singleton
    public SearchViewModel provideSearchViewModel(SearchViewModelFactory factory) {
        return factory.create(SearchViewModel.class);

    }

    @Provides
    public SearchViewModelFactory provideViewModelFactory(Repository repository) {
        return new SearchViewModelFactory(repository);
    }


}
