package com.app.thenhpattern.di.modules;

import com.app.thenhpattern.di.modules.FragmentBuilders.AuthBuilderModule;
import com.app.thenhpattern.di.modules.FragmentBuilders.MainBuilderModule;
import com.app.thenhpattern.di.modules.FragmentBuilders.PagerBuilderModule;
import com.app.thenhpattern.di.modules.viewmodules.AuthViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.MainViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.PagerViewModelModule;
import com.app.thenhpattern.view.AuthActivity;
import com.app.thenhpattern.view.MainActivity;
import com.app.thenhpattern.view.PagerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    // Modules Declared in the ContributesAndroidInjector
    // will be scoped for that particular Activity only

    @ContributesAndroidInjector(modules = {MainBuilderModule.class,MainViewModelModule.class})
    abstract MainActivity contributeMainActivty();

    @ContributesAndroidInjector(modules = {AuthBuilderModule.class,AuthViewModelModule.class})
    abstract AuthActivity contributeAuthActivty();

    @ContributesAndroidInjector(modules = {PagerBuilderModule.class, PagerViewModelModule.class})
    abstract PagerActivity contributePagerActivity();

}
