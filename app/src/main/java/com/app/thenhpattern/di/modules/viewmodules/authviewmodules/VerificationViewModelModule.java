package com.app.thenhpattern.di.modules.viewmodules.authviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.auth.SplashViewModel;
import com.app.thenhpattern.viewmodel.auth.VerificationViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class VerificationViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel.class)
    public abstract ViewModel bindVerificationViewModel(VerificationViewModel mainViewModel);
}
