package com.app.thenhpattern.viewmodel.main;


import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.remote.DataSource;
import com.app.thenhpattern.util.AbsentLiveData;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;


import javax.inject.Inject;
import javax.inject.Named;

public class SettingViewModel extends ViewModel {

    private DataSource dataSource;
    private MutableLiveData<Boolean> logoutRequest = new MutableLiveData<>();

    @Inject
    public SettingViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public final LiveData<Event<BaseResponse>> getLogoutStatus = Transformations.switchMap(logoutRequest, new Function<Boolean, LiveData<Event<BaseResponse>>>() {
        @Override
        public LiveData<Event<BaseResponse>> apply(Boolean status) {
            if(status == null || !status) {
                return AbsentLiveData.create();
            }else{
                return dataSource.getLogoutUser();
            }

        }
    });

    public void logoutUser() {
        logoutRequest.setValue(true);
    }

}
