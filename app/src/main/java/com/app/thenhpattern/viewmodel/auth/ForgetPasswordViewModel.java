package com.app.thenhpattern.viewmodel.auth;


import androidx.lifecycle.ViewModel;
import com.app.thenhpattern.MyApplication;
import com.app.thenhpattern.model.remote.DataSource;
import javax.inject.Inject;
import javax.inject.Named;

public class ForgetPasswordViewModel extends ViewModel {

    private DataSource dataSource;

    @Inject
    public ForgetPasswordViewModel(@Named(MyApplication.SERVICE_MODE) DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

}
