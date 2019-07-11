package com.app.thenhpattern.di.modules.viewmodules.authviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.auth.ForgetPasswordViewModel;
import com.app.thenhpattern.viewmodel.auth.VerificationViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ForgetPasswordViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForgetPasswordViewModel.class)
    public abstract ViewModel bindForgetPasswordViewModel(ForgetPasswordViewModel forgetPasswordViewModel);
}
