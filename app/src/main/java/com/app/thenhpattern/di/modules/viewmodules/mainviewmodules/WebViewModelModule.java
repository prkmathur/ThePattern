package com.app.thenhpattern.di.modules.viewmodules.mainviewmodules;

import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.di.modules.ViewModelKey;
import com.app.thenhpattern.viewmodel.main.setting.ProfileViewModel;
import com.app.thenhpattern.viewmodel.main.setting.WebviewViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class WebViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WebviewViewModel.class)
    public abstract ViewModel bindWebviewViewModel(WebviewViewModel webviewViewModel);
}
