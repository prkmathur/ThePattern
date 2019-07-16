package com.app.thenhpattern.di.modules.viewmodules.authviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.auth.RegisterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class RegisterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    public abstract ViewModel bindRegisterViewModel(RegisterViewModel registerViewModel);
}
