package com.app.thenhpattern.di.modules.viewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.AuthViewModel;
import com.app.thenhpattern.viewmodel.PagerViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PagerViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PagerViewModel.class)
    public abstract ViewModel bindPagerViewModel(PagerViewModel pagerViewModel);
}
