package com.app.thenhpattern.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;

import com.app.thenhpattern.databinding.ActivityMainBinding;
import com.app.thenhpattern.util.BaseActivity;
import com.app.thenhpattern.util.NavigationExtension;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.MainViewModel;
import com.app.thenhpattern.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    private LiveData<NavController> bottomController;
    private MainViewModel mainViewModel;
    private Toolbar mToolbar;
    private ArrayList<String> navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = (ActivityMainBinding) getBinding();
        mainViewModel = (MainViewModel) getViewModel();
        mainViewModel.setHeaderLeftListener(this);

        mToolbar = activityMainBinding.appToolbar;
        setSupportActionBar(mToolbar);

        if(savedInstanceState == null){
            setupBottomNavigationBar();
        }
        navigation = new ArrayList<>(
                Arrays.asList(
                        getApplicationContext().getString(R.string.label_home),
                        getApplicationContext().getString(R.string.label_jobs),
                        getApplicationContext().getString(R.string.label_notifications),
                        getApplicationContext().getString(R.string.label_settings)));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupBottomNavigationBar();
    }

    public Toolbar getToolbar(){
        return mToolbar;
    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Class setViewModelClass() {
        return MainViewModel.class;
    }

    private void setupBottomNavigationBar(){

        List navGraphIds = new ArrayList();
        navGraphIds.add(R.navigation.nav_graph_home);
        navGraphIds.add(R.navigation.nav_graph_job);
        navGraphIds.add(R.navigation.nav_graph_notifications);
        navGraphIds.add(R.navigation.nav_graph_settings);

        bottomController = NavigationExtension.setupWithNavController(
                activityMainBinding.bottomNav,
                navGraphIds,
                getSupportFragmentManager(),
                activityMainBinding.navHostContainer.getId(),
                getIntent()
        );

        bottomController.observe(this,bottomObserver);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return (bottomController.getValue() != null) ? bottomController.getValue().navigateUp() : false;

    }

    private final Observer<NavController> bottomObserver = new Observer<NavController>() {
        @Override
        public void onChanged(NavController navController) {
//            if(navController != null) {
//                navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
//                    activityMainBinding.appToolbar.setTitle(controller.getCurrentDestination().getLabel().toString());
//                    activityMainBinding.appToolbar.setMainVisibility(true);
//
//                    if (navigation.contains(controller.getCurrentDestination().getLabel())) {
//                        activityMainBinding.appToolbar.setHasLeft(false);
//                        activityMainBinding.appToolbar.setHasRight(true);
//                        activityMainBinding.appToolbar.setRightClickListener(MainActivity.this);
//                    } else {
//                        activityMainBinding.appToolbar.setHasLeft(true);
//                        activityMainBinding.appToolbar.setHasRight(false);
//                        activityMainBinding.appToolbar.setLeftClickListener(MainActivity.this);
//                    }
//
//                });
//            }
        }
    };

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
        mainViewModel.activityStatus().observe(this,observer);
    }

    private void removeObserver(){
        mainViewModel.activityStatus().removeObserver(observer);
    }

    private Observer<Boolean> observer = aBoolean -> {
        if(aBoolean){
            startActivity(new Intent(this,AuthActivity.class));
            finish();
        }
    };


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.left_icon){
            onSupportNavigateUp();
        }else if(v.getId() == R.id.right_icon){

        }

    }

}
