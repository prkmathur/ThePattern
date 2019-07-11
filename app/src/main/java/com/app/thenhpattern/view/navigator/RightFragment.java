package com.app.thenhpattern.view.navigator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.navigation.NavController;

import com.app.thenhpattern.R;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.PagerViewModel;
import com.app.thenhpattern.viewmodel.navigator.RightViewModel;


public class RightFragment extends BaseFragment {

    private NavController navController;
    private PagerViewModel pagerViewModel;

    public RightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerViewModel = (PagerViewModel) getParentViewModel();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_right;
    }

    @Override
    public Class setViewModelClass() {
        return RightViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return PagerViewModel.class;
    }

    @Override
    public void setObservers() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
    }

    @Override
    public void removeObservers() {

    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RightFragment","onResume");
        if(navController!= null) {
            pagerViewModel.setNavController(navController);
        }
    }

}
