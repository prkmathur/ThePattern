package com.app.thenhpattern.di.modules.viewmodules.navigatorviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.main.setting.ProfileViewModel;
import com.app.thenhpattern.viewmodel.navigator.RightViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class RightViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RightViewModel.class)
    public abstract ViewModel bindRightViewModel(RightViewModel rightViewModel);
}
