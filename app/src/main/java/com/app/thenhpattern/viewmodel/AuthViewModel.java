package com.app.thenhpattern.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.remote.DataSource;
import com.app.thenhpattern.model.remote.TaskRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class AuthViewModel extends ViewModel {

    private DataSource dataSource;

    MutableLiveData<Boolean> switchToMain = new MutableLiveData<>();

    @Inject
    public AuthViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public void setActivityChange(){
        switchToMain.setValue(true);
    }

    public LiveData<Boolean> activityStatus(){
        return switchToMain;
    }

}
