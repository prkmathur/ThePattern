package com.app.thenhpattern.viewmodel.auth;


import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.vo.UserVerification;
import com.app.thenhpattern.model.remote.DataSource;
import com.app.thenhpattern.util.AbsentLiveData;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;

import javax.inject.Inject;
import javax.inject.Named;

public class VerificationViewModel extends ViewModel {

    private DataSource dataSource;
    public MutableLiveData<Boolean> requestVerification = new MutableLiveData<>();
    public UserVerification.Request verificationRequest = new UserVerification.Request();

    @Inject
    public VerificationViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public final LiveData<Event<BaseResponse<UserVerification.Response>>> getVerifiedUser = Transformations.switchMap(requestVerification, new Function<Boolean, LiveData<Event<BaseResponse<UserVerification.Response>>>>() {
        @Override
        public LiveData<Event<BaseResponse<UserVerification.Response>>> apply(Boolean status) {
            if(status == null || !status) {
                return AbsentLiveData.create();
            }else{
                return dataSource.getVerifiedUser(verificationRequest);
            }
        }
    });

    public void verifyUser(){
        if(verificationRequest!= null) {
            requestVerification.setValue(true);
        }
    }

}
