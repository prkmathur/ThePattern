package com.app.thenhpattern.util;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import com.app.thenhpattern.R;
import com.app.thenhpattern.view.AuthActivity;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private ViewModel viewModel;
    private NavController navController;
    private ViewDataBinding viewDataBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(setViewModelClass()!=null) {
            viewModel = ViewModelProviders.of(this,viewModelFactory).get(setViewModelClass());
        }
        viewDataBinding = DataBindingUtil.setContentView(this,setContentLayout());

        if(this instanceof AuthActivity) {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        }

    }

    public abstract @LayoutRes int setContentLayout();

    public abstract Class setViewModelClass();

    public ViewModel getViewModel(){ return viewModel; }

    public NavController getNavController(){ return navController; }

    public ViewDataBinding getBinding(){ return viewDataBinding; }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}
