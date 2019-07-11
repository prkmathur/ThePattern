package com.app.thenhpattern.view.navigator;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;

import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentCenterBinding;
import com.app.thenhpattern.databinding.FragmentLeftBinding;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.NavigationExtension;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.PagerViewModel;
import com.app.thenhpattern.viewmodel.navigator.CenterViewModel;
import com.app.thenhpattern.viewmodel.navigator.LeftViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CenterFragment extends BaseFragment implements View.OnClickListener {

    private LiveData<NavController> bottomController;
    public static ArrayList<String> navigation;
    private FragmentCenterBinding binding;
    private PagerViewModel pagerViewModel;
    private NavController navController;

    public CenterFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pagerViewModel = (PagerViewModel) getParentViewModel();
        navigation = new ArrayList<>(
                Arrays.asList(
                        getActivity().getString(R.string.label_home),
                        getActivity().getString(R.string.label_jobs),
                        getActivity().getString(R.string.label_notifications),
                        getActivity().getString(R.string.label_settings)));

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = (FragmentCenterBinding) getBinding();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("selectedId",binding.bottomNav.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupBottomNavigationBar();
        if(savedInstanceState != null){
            int selectedId = savedInstanceState.getInt("selectedId");
            binding.bottomNav.setSelectedItemId(selectedId);
        }

    }

    private void setupBottomNavigationBar(){

        List navGraphIds = new ArrayList();
        navGraphIds.add(R.navigation.nav_graph_home);
        navGraphIds.add(R.navigation.nav_graph_job);
        navGraphIds.add(R.navigation.nav_graph_notifications);
        navGraphIds.add(R.navigation.nav_graph_settings);

        bottomController = NavigationExtension.setupWithNavController(
                binding.bottomNav,
                navGraphIds,
                getChildFragmentManager(),
                binding.navHostContainer.getId(),
                getActivity().getIntent()
        );

        bottomController.observe(this,bottomObserver);

    }

    private final Observer<NavController> bottomObserver = navController -> {
        if(navController != null) {

            this.navController = navController;

            pagerViewModel.setNavController(navController);

            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                binding.appToolbar.setTitle(controller.getCurrentDestination().getLabel().toString());
                binding.appToolbar.setMainVisibility(true);

                if (navigation.contains(controller.getCurrentDestination().getLabel())) {
                    binding.appToolbar.setHasLeft(false);
                    binding.appToolbar.setHasRight(true);
                    binding.appToolbar.setRightClickListener(CenterFragment.this);
                } else {
                    binding.appToolbar.setHasLeft(true);
                    binding.appToolbar.setHasRight(false);
                    binding.appToolbar.setLeftClickListener(CenterFragment.this);

                }
            });
        }
    };

    @Override
    public int setContentLayout() {
        return R.layout.fragment_center;
    }

    @Override
    public Class setViewModelClass() {
        return CenterViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return PagerViewModel.class;
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
    public void onClick(View v) {
        if(v.getId() == R.id.left_icon){
            onSupportNavigateUp();
        }else if(v.getId() == R.id.right_icon){
            pagerViewModel.switchProfile(true);
        }
    }

    private void onSupportNavigateUp() {
        if(bottomController.getValue() != null){
            bottomController.getValue().navigateUp();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(navController!= null) {
            pagerViewModel.setNavController(navController);
        }
    }
}
