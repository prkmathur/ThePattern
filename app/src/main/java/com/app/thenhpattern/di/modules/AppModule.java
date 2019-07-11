package com.app.thenhpattern.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.APIServices;
import com.app.thenhpattern.model.remote.DataSource;
import com.app.thenhpattern.model.remote.MockRepository;
import com.app.thenhpattern.model.remote.TaskRepository;
import com.app.thenhpattern.util.SessionManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    public static final String MyPREFERENCES = "MyPrefs" ;

    @Singleton
    @Provides
    APIServices provideAPIServices(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIServices.class);
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(MyApplication application){
        return application.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    @Singleton
    @Provides
    SessionManager provideTSessionManager(SharedPreferences sharedPreferences){
        return new SessionManager(sharedPreferences);
    }

    @Singleton
    @Provides
    @Named("MOCK")
    DataSource provideMockRepository(MyApplication application,SessionManager sessionManager){
        return new MockRepository(application,sessionManager);
    }

    @Singleton
    @Provides
    @Named("LIVE")
    DataSource provideTaskRepository(APIServices apiServices,SessionManager sessionManager,MyApplication application){
        return new TaskRepository(apiServices,sessionManager,application);
    }


}
