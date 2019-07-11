package com.app.thenhpattern.di.modules;
import androidx.lifecycle.ViewModelProvider;
import com.app.thenhpattern.util.ViewModelFactory;
import dagger.Binds;
import dagger.Module;

@Module
public  abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
