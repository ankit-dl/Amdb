package com.altimetric.amdb.utils;

import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {
    private EspressoIdlingResource() {

    }

    private static CountingIdlingResource resource = new CountingIdlingResource("GLOBAL");

    public static CountingIdlingResource getResource() {
        return resource;
    }

    public static void increment() {
        resource.increment();
    }

    public static void decrement() {
        if (!resource.isIdleNow()) resource.decrement();
    }

}
