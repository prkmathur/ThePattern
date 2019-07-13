package com.app.thenhpattern.view.auth;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;

import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentForgetPasswordBinding;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.auth.ForgetPasswordViewModel;

public class ForgetPasswordFragment extends BaseFragment implements View.OnClickListener {

    private NavController navController;
    private FragmentForgetPasswordBinding binding;

    public ForgetPasswordFragment() {}

    @Override
    public int setContentLayout() {
        return R.layout.fragment_forget_password;
    }

    @Override
    public Class setViewModelClass() {
        return ForgetPasswordViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
        binding = (FragmentForgetPasswordBinding) getBinding();
        binding.backBtn.setOnClickListener(this);
        binding.forgetSubmit.setOnClickListener(this);
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
        if(view.getId() == binding.backBtn.getId()){
            navController.navigateUp();
        }else if(view.getId() == binding.forgetSubmit.getId()){
            navController.navigate(R.id.action_forgetPasswordFragment_to_verificationFragment);
        }else if(view.getId() == binding.backBtn.getId()){
            navController.navigateUp();
        }else if(view.getId() == binding.forgetSubmit.getId()){
            navController.navigate(R.id.action_forgetPasswordFragment_to_verificationFragment);
        }
    }
}
