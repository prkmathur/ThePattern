package com.app.thenhpattern.di.modules.viewmodules.mainviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.main.NotificationViewModel;
import com.app.thenhpattern.viewmodel.main.SettingViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class NotificationViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    public abstract ViewModel bindNotificationViewModel(NotificationViewModel loginViewModel);
}
