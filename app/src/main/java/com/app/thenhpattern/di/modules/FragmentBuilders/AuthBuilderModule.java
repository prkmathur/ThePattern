package com.app.thenhpattern.di.modules.FragmentBuilders;

import com.app.thenhpattern.di.modules.viewmodules.authviewmodules.ForgetPasswordViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.authviewmodules.LoginViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.authviewmodules.RegisterViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.authviewmodules.SplashViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.authviewmodules.VerificationViewModelModule;
import com.app.thenhpattern.view.auth.ForgetPasswordFragment;
import com.app.thenhpattern.view.auth.LoginFragment;
import com.app.thenhpattern.view.auth.RegisterFragment;
import com.app.thenhpattern.view.auth.SplashFragment;
import com.app.thenhpattern.view.auth.VerificationFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthBuilderModule {

    @ContributesAndroidInjector(modules = LoginViewModelModule.class)
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector(modules = RegisterViewModelModule.class)
    abstract RegisterFragment contributeRegisterFragment();

    @ContributesAndroidInjector(modules = SplashViewModelModule.class)
    abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector(modules = VerificationViewModelModule.class)
    abstract VerificationFragment contributeVerificationFragment();

    @ContributesAndroidInjector(modules = ForgetPasswordViewModelModule.class)
    abstract ForgetPasswordFragment contributeForgetPasswordFragment();

}
