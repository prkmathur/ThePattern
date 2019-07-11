package com.app.thenhpattern.di.components;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.di.modules.ActivityBuilderModule;
import com.app.thenhpattern.di.modules.AppModule;
import com.app.thenhpattern.di.modules.ViewModelFactoryModule;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        ViewModelFactoryModule.class
        })
public abstract class AppComponent implements AndroidInjector<MyApplication> {

        @Component.Builder
        public interface Builder{
            @BindsInstance
            Builder application(MyApplication application);

            AppComponent build();
        }

}
