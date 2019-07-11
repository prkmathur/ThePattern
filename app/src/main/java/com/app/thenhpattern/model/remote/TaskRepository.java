package com.app.thenhpattern.model.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.R;
import com.app.thenhpattern.model.APIServices;
import com.app.thenhpattern.model.vo.User;
import com.app.thenhpattern.model.vo.UserVerification;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;
import com.app.thenhpattern.util.SessionManager;

import javax.inject.Inject;

public class TaskRepository implements DataSource {

    private static final String TAG = "TaskRepository";

    private APIServices apiServices;
    private SessionManager sessionManager;
    private MyApplication application;

    @Inject
    public TaskRepository(APIServices apiServices,SessionManager sessionManager,MyApplication application) {
        this.apiServices = apiServices;
        this.sessionManager = sessionManager;
        this.application = application;
    }

    @Override
    public MutableLiveData<Event<Boolean>> getIsVersionUpToDate() {

        MutableLiveData<Event<Boolean>> isVersionUpToDate = new MutableLiveData<>();

        // TODO : Call the getIsVersionUpToDate API

        return isVersionUpToDate;

    }

    @Override
    public LiveData<Event<BaseResponse<User.CurrentUser>>> getRegisterUser(User.RegisterRequest request) {

        MutableLiveData<Event<BaseResponse<User.CurrentUser>>> registeredUserResponse = new MutableLiveData<>();

        Log.d(TAG,request.getRequestJson());
        
        // TODO : Call the getRegisterUser API

        return registeredUserResponse;
    }

    @Override
    public LiveData<Event<BaseResponse<User.CurrentUser>>> getLoginUser(User.LoginRequest request) {

        MutableLiveData<Event<BaseResponse<User.CurrentUser>>> loginUserResponse = new MutableLiveData<>();

        Log.d(TAG,request.getRequestJson());

        // TODO : Call the getLoginUser API

        return loginUserResponse;
    }

    @Override
    public LiveData<Event<BaseResponse<UserVerification.Response>>> getVerifiedUser(UserVerification.Request request) {

        MutableLiveData<Event<BaseResponse<UserVerification.Response>>> registeredUserResponse = new MutableLiveData<>();

        // TODO : Call the getVerifiedUser API

        return registeredUserResponse;
    }

    @Override
    public LiveData<Event<BaseResponse>> getLogoutUser() {

        MutableLiveData<Event<BaseResponse>> logoutResponse = new MutableLiveData<>();

        // TODO : Call the getLogoutUser API

        return logoutResponse;
    }

}
