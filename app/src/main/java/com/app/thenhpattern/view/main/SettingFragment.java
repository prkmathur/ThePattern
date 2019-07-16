package com.app.thenhpattern.view.main;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentSettingBinding;
import com.app.thenhpattern.model.adapters.SettingAdapter;
import com.app.thenhpattern.model.handler.ISettingItemEventListner;
import com.app.thenhpattern.model.vo.SettingModel;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.PagerViewModel;
import com.app.thenhpattern.viewmodel.main.SettingViewModel;

import java.util.ArrayList;

public class SettingFragment extends BaseFragment implements ISettingItemEventListner {

    private SettingViewModel settingViewModel;
    private PagerViewModel mainViewModel;
    private NavController navController;
    private FragmentSettingBinding fragmentSettingBinding;
    private ArrayList<SettingModel> settingModels = new ArrayList<>();
    private SettingAdapter settingAdapter;

    public static final int PROFILE=0;
    public static final int ABOUT_US=1;
    public static final int PRIVACY_POLICY=2;
    public static final int CHANGE_PASSWORD=3;
    public static final int LOGOUT=4;

    public SettingFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Act: SettingFragment","onCreate");

        settingViewModel = (SettingViewModel) getViewModel();

        mainViewModel = (PagerViewModel) getParentViewModel();

        settingModels.add(SettingModel.createItem(R.drawable.ic_user,getActivity().getString(R.string.label_profile),PROFILE));
        settingModels.add(SettingModel.createItem(R.drawable.ic_user,getActivity().getString(R.string.label_aboutus),ABOUT_US));
        settingModels.add(SettingModel.createItem(R.drawable.ic_user,getActivity().getString(R.string.label_privacypolicy),PRIVACY_POLICY));
        settingModels.add(SettingModel.createItem(R.drawable.ic_user,getActivity().getString(R.string.label_changepassword),CHANGE_PASSWORD));
        settingModels.add(SettingModel.createItem(R.drawable.ic_user,getActivity().getString(R.string.label_logout),LOGOUT));

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
        fragmentSettingBinding = (FragmentSettingBinding) getBinding();
        settingAdapter = new SettingAdapter(settingModels,this);
        fragmentSettingBinding.recycleSeetings.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentSettingBinding.recycleSeetings.setAdapter(settingAdapter);
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public Class setViewModelClass() {
        return SettingViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return PagerViewModel.class;
    }


    @Override
    public void setObservers() {
        settingViewModel.getLogoutStatus.observe(this,observer);
    }

    @Override
    public void removeObservers() {
        settingViewModel.getLogoutStatus.removeObserver(observer);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    private final Observer<Event<BaseResponse>> observer = baseResponse -> {

        if(baseResponse.getContentIfNotHandled() != null){
            if(baseResponse.peekContent().isStatus()){
                mainViewModel.setActivityChange();
            }
        }

    };

    @Override
    public void onItemClicked(SettingModel settingModel) {

        switch (settingModel.getItemId()){

            case PROFILE :
                navController.navigate(R.id.action_settingFragment_to_profileFragment);
                break;

            case ABOUT_US :
                navController.navigate(R.id.action_settingFragment_to_webViewFragment);
                break;

            case PRIVACY_POLICY :
                navController.navigate(R.id.action_settingFragment_to_webViewFragment);
                break;

            case CHANGE_PASSWORD :
                navController.navigate(R.id.action_settingFragment_to_changePasswordFragment);
                break;

            case LOGOUT :
                settingViewModel.logoutUser();
                break;

        }

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
