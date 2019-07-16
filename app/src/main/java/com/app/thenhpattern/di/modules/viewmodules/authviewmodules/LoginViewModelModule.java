package com.app.thenhpattern.di.modules.viewmodules.authviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.auth.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    public abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
}
