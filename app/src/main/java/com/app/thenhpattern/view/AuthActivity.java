package com.app.thenhpattern.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.app.thenhpattern.R;
import com.app.thenhpattern.util.BaseActivity;
import com.app.thenhpattern.viewmodel.AuthViewModel;

public class AuthActivity extends BaseActivity {

    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = (AuthViewModel) getViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setObserver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeObserver();
    }

    private void setObserver(){
        viewModel.activityStatus().observe(this,observer);
    }

    private void removeObserver(){
        viewModel.activityStatus().removeObserver(observer);
    }

    @Override
    public int setContentLayout() { return R.layout.activity_auth; }

    @Override
    public Class setViewModelClass() { return AuthViewModel.class; }

    private Observer<Boolean> observer = aBoolean -> {
        if(aBoolean){
            startActivity(new Intent(this,PagerActivity.class));
            finish();
        }
    };

}
