package com.app.thenhpattern.model.remote;

import androidx.lifecycle.LiveData;

import com.app.thenhpattern.model.vo.User;
import com.app.thenhpattern.model.vo.UserVerification;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;

public interface DataSource {

    LiveData<Event<Boolean>> getIsVersionUpToDate();

    LiveData<Event<BaseResponse<User.CurrentUser>>> getRegisterUser(User.RegisterRequest request);

    LiveData<Event<BaseResponse<User.CurrentUser>>> getLoginUser(User.LoginRequest request);

    LiveData<Event<BaseResponse<UserVerification.Response>>> getVerifiedUser(UserVerification.Request request);

    LiveData<Event<BaseResponse>> getLogoutUser();

}
