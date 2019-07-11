package com.app.thenhpattern.di.modules.viewmodules.mainviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.auth.LoginViewModel;
import com.app.thenhpattern.viewmodel.main.JobViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class JobViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(JobViewModel.class)
    public abstract ViewModel bindJobViewModel(JobViewModel loginViewModel);
}
