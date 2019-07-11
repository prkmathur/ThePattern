package com.app.thenhpattern.di.modules.FragmentBuilders;

import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.ChangePasswordViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.HomeViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.JobViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.NotificationViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.ProfileViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.SettingViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.mainviewmodules.WebViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.navigatorviewmodules.CenterViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.navigatorviewmodules.LeftViewModelModule;
import com.app.thenhpattern.di.modules.viewmodules.navigatorviewmodules.RightViewModelModule;

import com.app.thenhpattern.view.main.HomeFragment;
import com.app.thenhpattern.view.main.JobFragment;
import com.app.thenhpattern.view.main.NotificationsFragment;
import com.app.thenhpattern.view.main.SettingFragment;
import com.app.thenhpattern.view.main.setting.ChangePasswordFragment;
import com.app.thenhpattern.view.main.setting.ProfileFragment;
import com.app.thenhpattern.view.main.setting.WebViewFragment;
import com.app.thenhpattern.view.navigator.CenterFragment;
import com.app.thenhpattern.view.navigator.LeftFragment;
import com.app.thenhpattern.view.navigator.RightFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PagerBuilderModule {

    @ContributesAndroidInjector(modules = LeftViewModelModule.class)
    abstract LeftFragment contributeLeftFragment();

    @ContributesAndroidInjector(modules = RightViewModelModule.class)
    abstract RightFragment contributeRightFragment();

    @ContributesAndroidInjector(modules = CenterViewModelModule.class)
    abstract CenterFragment contributeCenterFragment();

    @ContributesAndroidInjector(modules = HomeViewModelModule.class)
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector(modules = JobViewModelModule.class)
    abstract JobFragment contributeJobFragment();

    @ContributesAndroidInjector(modules = SettingViewModelModule.class)
    abstract SettingFragment contributeSettingFragment();

    @ContributesAndroidInjector(modules = ProfileViewModelModule.class)
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector(modules = WebViewModelModule.class)
    abstract WebViewFragment contributeWebViewFragment();

    @ContributesAndroidInjector(modules = ChangePasswordViewModelModule.class)
    abstract ChangePasswordFragment contributeChangePasswordFragment();

    @ContributesAndroidInjector(modules = NotificationViewModelModule.class)
    abstract NotificationsFragment contributeNotificationsFragment();

}
