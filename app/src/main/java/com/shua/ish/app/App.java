package com.shua.ish.app;

import android.app.Application;

import io.vov.vitamio.Vitamio;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Vitamio.isInitialized(getApplicationContext());
    }
}
