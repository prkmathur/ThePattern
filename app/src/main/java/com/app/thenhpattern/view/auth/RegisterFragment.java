package com.app.thenhpattern.view.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;

import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentRegisterBinding;
import com.app.thenhpattern.model.vo.User;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.AuthViewModel;
import com.app.thenhpattern.viewmodel.auth.RegisterViewModel;

public class RegisterFragment extends BaseFragment implements View.OnClickListener {

    private RegisterViewModel registerViewModel;
    private AuthViewModel authViewModel;
    private NavController navController;
    private FragmentRegisterBinding binding;

    public  RegisterFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerViewModel = (RegisterViewModel) getViewModel();
        authViewModel = (AuthViewModel) getParentViewModel();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = getNavController();
        binding = (FragmentRegisterBinding) getBinding();
        binding.setData(registerViewModel);
        binding.backBtn.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);
    }

    @Override
    public Class setViewModelClass() {
        return RegisterViewModel.class;
    }

    @Override
    public Class setParentViewModelClass() {
        return AuthViewModel.class;
    }

    @Override
    public void setObservers() { registerViewModel.getRegisterStatus.observe(getViewLifecycleOwner(),observer); }

    @Override
    public void removeObservers() {
        registerViewModel.getRegisterStatus.removeObserver(observer);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
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

    private Observer<Event<BaseResponse<User.CurrentUser>>> observer = currentUserBaseResponse -> {

        if(currentUserBaseResponse.getContentIfNotHandled() != null) {
            if (currentUserBaseResponse.peekContent().isStatus()) {
                navController.navigate(R.id.action_registerFragment_to_verificationFragment, null,
                        new NavOptions.Builder()
                                .setPopUpTo(R.id.registerFragment, true)
                                .build());
            } else {
                binding.loadingLayout.setVisibility(View.GONE);
                Toast.makeText(getContext(), currentUserBaseResponse.peekContent().getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.backBtn.getId()){
            if(navController!= null)
                navController.navigateUp();
        }else if(view.getId() == binding.btnSubmit.getId()){
            registerUserRequest();
        }
    }

    private void registerUserRequest() {
        binding.loadingLayout.setVisibility(View.VISIBLE);
        registerViewModel.registerUser();
    }
}
