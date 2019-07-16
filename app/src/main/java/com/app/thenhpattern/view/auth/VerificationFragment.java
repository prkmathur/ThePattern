package com.app.thenhpattern.view.auth;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import android.view.View;
import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentVerificationBinding;
import com.app.thenhpattern.model.vo.UserVerification;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;
import com.app.thenhpattern.util.SessionManager;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.AuthViewModel;
import com.app.thenhpattern.viewmodel.auth.VerificationViewModel;

import javax.inject.Inject;


public class VerificationFragment extends BaseFragment implements View.OnClickListener {

    private VerificationViewModel verificationViewModel;
    private AuthViewModel authViewModel;
    private NavController navController;
    private FragmentVerificationBinding binding;

    @Inject
    SessionManager sessionManager;

    public VerificationFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificationViewModel = (VerificationViewModel) getViewModel();
        authViewModel = (AuthViewModel) getParentViewModel();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
        binding = (FragmentVerificationBinding) getBinding();
        binding.setData(verificationViewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        setObservers();
    }

    @Override
    public void onPause() {
        super.onPause();
        removeObservers();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_verification;
    }

    @Override
    public Class setViewModelClass() {
        return VerificationViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return AuthViewModel.class;
    }

    @Override
    public void setObservers() {
        verificationViewModel.getVerifiedUser.observe(getViewLifecycleOwner(),observer);
    }

    @Override
    public void removeObservers() {
        verificationViewModel.getVerifiedUser.removeObserver(observer);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    private Observer<Event<BaseResponse<UserVerification.Response>>> observer = responseBaseResponse -> {

    };

    @Override
    public void onClick(View view) {

    }

}
