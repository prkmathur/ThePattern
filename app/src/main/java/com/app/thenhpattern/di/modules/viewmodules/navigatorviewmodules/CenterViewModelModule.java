package com.app.thenhpattern.di.modules.viewmodules.navigatorviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.main.setting.ProfileViewModel;
import com.app.thenhpattern.viewmodel.navigator.CenterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CenterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CenterViewModel.class)
    public abstract ViewModel bindCenterViewModel(CenterViewModel centerViewModel);
}
