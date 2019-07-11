package com.app.thenhpattern.di.modules.viewmodules.mainviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.main.SettingViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SettingViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel.class)
    public abstract ViewModel bindSettingViewModel(SettingViewModel loginViewModel);
}
