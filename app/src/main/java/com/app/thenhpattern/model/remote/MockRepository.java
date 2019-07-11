package com.app.thenhpattern.model.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.R;
import com.app.thenhpattern.model.vo.User;
import com.app.thenhpattern.model.vo.UserVerification;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import com.app.thenhpattern.util.Event;

public class MockRepository implements DataSource {

    private MyApplication application;
    private SessionManager sessionManager;

    public MockRepository(MyApplication application,SessionManager sessionManager) {
        this.application = application;
        this.sessionManager = sessionManager;
    }

    @Override
    public LiveData<Event<Boolean>> getIsVersionUpToDate() {

        MutableLiveData<Event<Boolean>> isVersionUpToDate = new MutableLiveData<>();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                isVersionUpToDate.postValue(new Event(true));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return isVersionUpToDate;
    }

    @Override
    public LiveData<Event<BaseResponse<User.CurrentUser>>> getRegisterUser(User.RegisterRequest request) {

        MutableLiveData<Event<BaseResponse<User.CurrentUser>>> baseResponseMutableLiveData = new MutableLiveData<>();

        Gson gson = new Gson();

        Type collectionType = new TypeToken<BaseResponse<User.CurrentUser>>(){}.getType();

        String response = loadJSONFromAsset("register.json");

        if(isValidJson(response)) {

            BaseResponse<User.CurrentUser> responseBaseResponse = gson.fromJson(response, collectionType);

            if(responseBaseResponse.isStatus()){
                if(sessionManager.setUser(responseBaseResponse.getData())){
                    baseResponseMutableLiveData.setValue(new Event(responseBaseResponse));
                }else{
                    responseBaseResponse.setData(null);
                    responseBaseResponse.setStatus(false);
                    responseBaseResponse.setMessage(application.getApplicationContext().getString(R.string.PREF_COMMIT_ERROR));
                    baseResponseMutableLiveData.setValue(new Event(responseBaseResponse));
                }
            }

            return baseResponseMutableLiveData;
        }

        return null;
    }

    @Override
    public LiveData<Event<BaseResponse<User.CurrentUser>>> getLoginUser(User.LoginRequest request) {

        MutableLiveData<Event<BaseResponse<User.CurrentUser>>> baseResponseMutableLiveData = new MutableLiveData<>();

        Gson gson = new Gson();

        Type collectionType = new TypeToken<BaseResponse<User.CurrentUser>>(){}.getType();

        String response = loadJSONFromAsset("login.json");

        if(isValidJson(response)) {

            BaseResponse<User.CurrentUser> responseBaseResponse = gson.fromJson(response, collectionType);

            if(responseBaseResponse.isStatus()){
                if(sessionManager.setUser(responseBaseResponse.getData())){
                    baseResponseMutableLiveData.setValue(new Event(responseBaseResponse));
                }else{
                    responseBaseResponse.setData(null);
                    responseBaseResponse.setStatus(false);
                    responseBaseResponse.setMessage(application.getApplicationContext().getString(R.string.PREF_COMMIT_ERROR));
                    baseResponseMutableLiveData.setValue(new Event(responseBaseResponse));
                }
            }


            return baseResponseMutableLiveData;
        }

        return null;
    }

    @Override
    public LiveData<Event<BaseResponse<UserVerification.Response>>> getVerifiedUser(UserVerification.Request request) {
        return null;
    }

    @Override
    public LiveData<Event<BaseResponse>> getLogoutUser() {

        MutableLiveData<Event<BaseResponse>> baseResponseMutableLiveData = new MutableLiveData<>();
        BaseResponse responseBaseResponse = new BaseResponse();
        responseBaseResponse.setStatus(true);
        responseBaseResponse.setMessage("Successfully Logout");

        sessionManager.logoutUser();

        baseResponseMutableLiveData.postValue(new Event(responseBaseResponse));

        return baseResponseMutableLiveData;

    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = application.getApplicationContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private Boolean isValidJson(String response){
        try {
            new JSONObject(response);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(response);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
