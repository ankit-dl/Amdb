package com.altimetric.amdb.viewmodel;

import androidx.lifecycle.LiveData;

import com.altimetric.amdb.model.Repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class SearchViewModelTest {

    SearchViewModel SUT;
    @Mock
    Repository repository;

    @Before
    public void setUp() throws Exception {
        SUT = new SearchViewModel(repository);
    }

    @Test
    public void test_null() {


        Mockito.when(repository.getSearchLiveDate()).thenReturn(null);
        assertNull(SUT.getSearchLiveDate());

        Mockito.when(repository.getLoadingState()).thenReturn(null);
        assertNull(SUT.getLoadingState());

        Mockito.when(repository.getSearchResult("test")).thenReturn(null);
        assertNull(SUT.getSearchResult(""));
    }

    @Test
    public void test_not_null() {

        Mockito.when(repository.getSearchLiveDate()).thenReturn(Mockito.mock(LiveData.class));
        assertNotNull(SUT.getSearchLiveDate());

        Mockito.when(repository.getLoadingState()).thenReturn(Mockito.mock(LiveData.class));
        assertNotNull(SUT.getLoadingState());

        Mockito.when(repository.getSearchResult("test")).thenReturn(Mockito.mock(LiveData.class));
        assertNotNull(SUT.getSearchResult("test"));

    }
}