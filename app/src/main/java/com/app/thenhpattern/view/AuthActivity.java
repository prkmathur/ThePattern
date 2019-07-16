package com.app.thenhpattern.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.app.thenhpattern.R;
import com.app.thenhpattern.util.BaseActivity;
import com.app.thenhpattern.viewmodel.AuthViewModel;

public class AuthActivity extends BaseActivity {

    private AuthViewModel viewModel;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = (AuthViewModel) getViewModel();
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
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

    @Override
    public boolean onSupportNavigateUp() {
        return (navController!= null) ? navController.navigateUp() : false;

    }

    @Override
    public void onBackPressed() {

        //TODO: Need to implement a better approach to handle the back stack clear task

        if(navController.getCurrentDestination().getLabel().equals(getString(R.string.label_fragment_verification))){

            // Finish the application
            finish();

        }else{
            navController.navigateUp();
        }
    }
}
