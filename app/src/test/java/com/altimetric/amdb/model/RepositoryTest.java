package com.altimetric.amdb.model;

import androidx.arch.core.executor.DefaultTaskExecutor;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.altimetric.amdb.model.remote.ApiService;
import com.altimetric.amdb.model.remote.BaseResult;
import com.altimetric.amdb.model.remote.SearchResult;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.mockito.junit.MockitoJUnitRunner;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    Repository SUT;
    @Mock
    ApiService service;
    @Mock
    Observable<BaseResult> resp;
    @Rule
    public InstantTaskExecutorRule executor = new InstantTaskExecutorRule();
   @Mock
    LifecycleOwner owner;
    @Before
    public void setUp() {
        SUT = new Repository(service);
    }

    @Test
    public void test_search() {
        Mockito.when(service.getSearchResult("test")).thenReturn(resp);
        SUT.getSearchLiveDate().observeForever(searchResults -> {
          assertEquals(searchResults.size(),34);
        });
        SUT.getSearchResult("test");
        Mockito.verify(service, Mockito.times(1)).getSearchResult("test");

        assertNotNull(SUT.getSearchResult("test"));
        assertNotNull(SUT.getLoadingState());
        assertNotNull(SUT.getSearchLiveDate());


    }
}