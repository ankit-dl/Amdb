package com.altimetric.amdb.view;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.altimetric.amdb.R;
import com.altimetric.amdb.utils.EspressoIdlingResource;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Matches;

import java.util.concurrent.RecursiveAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SearchActivityTest {
    @Rule
    public ActivityTestRule<SearchActivity> testRule = new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void registerIdleResource() throws Exception {
        IdlingRegistry.getInstance()
                .register(EspressoIdlingResource.getResource());
    }

    @After
    public void unregisterIdlingResource() throws Exception {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getResource());
    }

    @Test
    public void test_fragment_added() {

        onView(withId(R.id.search_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.search_edit_text)).perform(typeText("toy"), closeSoftKeyboard());
        onView(withId(R.id.search_icon)).perform(click());
        onView(withId(R.id.main_progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        onView(withId(R.id.main_result_recycler_view)).check((view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            int c=adapter.getItemCount();
            System.out.println(c);
            if (c==0) assert  true;
           // assertThat(adapter.getItemCount()));
        });

        onView(withId(R.id.main_result_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(R.id.addcart);
                v.performClick();


            }
        }));

        onView(withId(R.id.cartitem)).perform(click());

        onView(withId(R.id.cartRV)).check(matches(isDisplayed()));
        onView(withId(R.id.cartRV)).check((view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            int c=adapter.getItemCount();
            System.out.println(c);
            if (c>0) assert  true;
            // assertThat(adapter.getItemCount()));
        });
        pressBack();



    }
}