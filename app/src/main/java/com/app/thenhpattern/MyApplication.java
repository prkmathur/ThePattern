package com.app.thenhpattern;


import com.app.thenhpattern.di.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MyApplication extends DaggerApplication {

    public static final String SERVICE_MODE = "MOCK"; // LIVE Or MOCK

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
