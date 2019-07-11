package com.app.thenhpattern.view.main;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.navigation.NavController;
import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentHomeBinding;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.MainViewModel;
import com.app.thenhpattern.viewmodel.main.HomeViewModel;


public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private MainViewModel mainViewModel;
    private NavController navController;
    private FragmentHomeBinding fragmentHomeBinding;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Act: HomeFragment","onCreate");
        homeViewModel = (HomeViewModel) getViewModel();
        mainViewModel = (MainViewModel) getParentViewModel();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
        fragmentHomeBinding = (FragmentHomeBinding) getBinding();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public Class setViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return null;
    }

    @Override
    public void setObservers() {

    }

    @Override
    public void removeObservers() {

    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPause() {
        super.onPause();
        removeObservers();
    }

    @Override
    public void onResume() {
        super.onResume();
        setObservers();
    }
}
