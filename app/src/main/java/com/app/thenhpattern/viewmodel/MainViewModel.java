package com.app.thenhpattern.viewmodel;


import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.remote.DataSource;
import javax.inject.Inject;
import javax.inject.Named;

public class MainViewModel extends ViewModel {

    private DataSource dataSource;
    MutableLiveData<Boolean> switchToAuth = new MutableLiveData<>();
    private View.OnClickListener headerLeftOnClickListener;


    @Inject

    public MainViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }
    public void setActivityChange(){
        switchToAuth.setValue(true);
    }

    public void setHeaderLeftListener(View.OnClickListener headerLeftOnClickListener){
        this.headerLeftOnClickListener = headerLeftOnClickListener;
    }

    public View.OnClickListener getHeaderLeftListener(){
       return headerLeftOnClickListener;
    }

    public LiveData<Boolean> activityStatus(){
        return switchToAuth;
    }


}
