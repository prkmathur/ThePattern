package com.app.thenhpattern.di.modules.viewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel mainViewModel);
}
