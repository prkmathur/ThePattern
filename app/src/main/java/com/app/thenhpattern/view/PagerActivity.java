package com.app.thenhpattern.view;

import android.content.Intent;
import android.os.Bundle;

import com.app.thenhpattern.databinding.ActivityViewPagerBinding;
import com.app.thenhpattern.util.BaseActivity;
import com.app.thenhpattern.view.navigator.CenterFragment;
import com.app.thenhpattern.viewmodel.PagerViewModel;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.app.thenhpattern.R;

public class PagerActivity extends BaseActivity {

    private ActivityViewPagerBinding activityViewPagerBinding;
    private MyPagerAdapter adapterViewPager;
    private NavController navController;
    private PagerViewModel pagerViewModel;
    private NavHostFragment leftWing;
    private NavHostFragment rightWing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityViewPagerBinding = (ActivityViewPagerBinding) getBinding();

        pagerViewModel = (PagerViewModel) getViewModel();

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());

        activityViewPagerBinding.viewPager.setAdapter(adapterViewPager);

        activityViewPagerBinding.viewPager.setCurrentItem(1);



    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_view_pager;
    }

    @Override
    public Class setViewModelClass() {
        return PagerViewModel.class;
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        private int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return getLeftWing();
                case 1:
                    return new CenterFragment();
                case 2:
                    return getRightWing();
                default:
                    return null;
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        pagerViewModel.controllerLiveData().observe(this,observer1);
        pagerViewModel.profileSwitchLiveData().observe(this,observer2);
        pagerViewModel.activityStatus().observe(this,observer3);
    }

    @Override
    protected void onStop() {
        super.onStop();
        pagerViewModel.controllerLiveData().observe(this,observer1);
        pagerViewModel.profileSwitchLiveData().observe(this,observer2);
        pagerViewModel.activityStatus().removeObserver(observer3);
    }

    private Observer<Boolean> observer3 = aBoolean -> {
        if(aBoolean){
            startActivity(new Intent(this,AuthActivity.class));
            finish();
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        return (navController != null) ? navController.navigateUp() : false;

    }

    private Observer<NavController> observer1 = navController -> {
            this.navController = navController;
    };

    private Observer<Boolean> observer2 = aBoolean -> {

        if(aBoolean){
            activityViewPagerBinding.viewPager.setCurrentItem(2,true);
        }

    };

    @Override
    public void onBackPressed() {
        if(navController != null){
            // For Center Fragment
            if(CenterFragment.navigation.contains(navController.getCurrentDestination().getLabel())){

                if(activityViewPagerBinding.viewPager.getCurrentItem() == 1){
                    super.onBackPressed();
                }else{
                    activityViewPagerBinding.viewPager.setCurrentItem(1);
                }
            }else{
                navController.navigateUp();
            }

        }else{
            super.onBackPressed();
        }
    }

    private NavHostFragment getLeftWing(){

        if(leftWing == null) {
            leftWing = NavHostFragment.create(R.navigation.nav_graph_left_wing);
        }
        return leftWing;
    }

    private NavHostFragment getRightWing(){

        if(rightWing == null) {
            rightWing = NavHostFragment.create(R.navigation.nav_graph_right_wing);
        }
        return rightWing;
    }

}
