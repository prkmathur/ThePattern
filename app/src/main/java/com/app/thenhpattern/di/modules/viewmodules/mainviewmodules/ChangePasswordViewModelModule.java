package com.app.thenhpattern.di.modules.viewmodules.mainviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.main.SettingViewModel;
import com.app.thenhpattern.viewmodel.main.setting.ChangePasswordViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ChangePasswordViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel.class)
    public abstract ViewModel bindChangePasswordViewModel(ChangePasswordViewModel changePasswordViewModel);
}
