package com.app.thenhpattern.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.remote.DataSource;

import javax.inject.Inject;
import javax.inject.Named;

public class PagerViewModel extends ViewModel {

    private DataSource dataSource;
    private MutableLiveData<Boolean> switchToAuth = new MutableLiveData<>();
    private MutableLiveData<NavController> navController = new MutableLiveData<>();
    private MutableLiveData<Boolean> switchToProfile = new MutableLiveData<>();

    @Inject
    public PagerViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }
    public void setActivityChange(){
        switchToAuth.setValue(true);
    }

    public LiveData<Boolean> activityStatus(){
        return switchToAuth;
    }

    public LiveData<NavController> controllerLiveData(){

        return navController;
    }

    public LiveData<Boolean> profileSwitchLiveData(){

        return switchToProfile;
    }

    public void setNavController(NavController navController){
        this.navController.setValue(navController);
    }

    public void switchProfile(Boolean aBoolean){
        this.switchToProfile.setValue(aBoolean);
    }

}
