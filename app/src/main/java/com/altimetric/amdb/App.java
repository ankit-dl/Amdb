package com.altimetric.amdb;

import android.app.Application;

import com.altimetric.amdb.di.components.ApplicationComponent;
import com.altimetric.amdb.di.components.DaggerApplicationComponent;

public class App extends Application {
    private static App app;
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        component= DaggerApplicationComponent.factory().build(this);

    }

    public static App getInstance() {
        return app;
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
