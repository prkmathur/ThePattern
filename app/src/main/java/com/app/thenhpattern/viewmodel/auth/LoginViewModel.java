package com.app.thenhpattern.viewmodel.auth;


import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.vo.User;
import com.app.thenhpattern.model.remote.DataSource;
import com.app.thenhpattern.util.AbsentLiveData;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;

import javax.inject.Inject;
import javax.inject.Named;

public class LoginViewModel extends ViewModel {

    private DataSource dataSource;
    private MutableLiveData<Boolean> requestLogin = new MutableLiveData<>();
    public  User.LoginRequest loginRequest = new User.LoginRequest();

    @Inject
    public LoginViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public void requestLogin(){
        requestLogin.setValue(true);
    }

    public final LiveData<Event<BaseResponse<User.CurrentUser>>> loginResponse = Transformations.switchMap(requestLogin, new Function<Boolean, LiveData<Event<BaseResponse<User.CurrentUser>>>>() {
        @Override
        public LiveData<Event<BaseResponse<User.CurrentUser>>> apply(Boolean status) {

            if(status == null || !status) {
                return AbsentLiveData.create();
            }else{
                return dataSource.getLoginUser(loginRequest);
            }

        }
    });

}
