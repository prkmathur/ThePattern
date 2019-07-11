package com.app.thenhpattern.viewmodel.main.setting;


import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.remote.DataSource;

import javax.inject.Inject;
import javax.inject.Named;

public class ProfileViewModel extends ViewModel {

    private DataSource dataSource;

    @Inject
    public ProfileViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }


}
