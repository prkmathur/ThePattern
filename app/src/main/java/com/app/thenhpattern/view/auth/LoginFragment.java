package com.app.thenhpattern.view.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import com.app.thenhpattern.R;
import com.app.thenhpattern.databinding.FragmentLoginBinding;
import com.app.thenhpattern.model.vo.User;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.BaseResponse;
import com.app.thenhpattern.util.Event;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.AuthViewModel;
import com.app.thenhpattern.viewmodel.auth.LoginViewModel;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    private AuthViewModel authViewModel;
    private NavController navController;
    private FragmentLoginBinding binding;

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = (LoginViewModel) getViewModel();
        authViewModel = (AuthViewModel) getParentViewModel();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public Class setViewModelClass() { return LoginViewModel.class; }

    @Override
    public Class setParentViewModelClass() {
        return AuthViewModel.class;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= getNavController();
        binding = (FragmentLoginBinding) getBinding();
        binding.setData(loginViewModel);
        binding.btnRegister.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        binding.txtForget.setOnClickListener(this);
    }

    @Override
    public void setObservers() {
        loginViewModel.loginResponse.observe(getViewLifecycleOwner(),observer);
    }

    @Override
    public void removeObservers() {
        loginViewModel.loginResponse.removeObserver(observer);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_register){
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        }else if (v.getId() == R.id.btn_login){
            if(binding.edtEmail.getText() == null || binding.edtEmail.getText().toString().trim().length() == 0){
                binding.edtEmail.setError(getActivity().getString(R.string.error_email));
            }else if(binding.edtPassword.getText() == null || binding.edtPassword.getText().toString().trim().length() == 0){
                binding.edtPassword.setError(getActivity().getString(R.string.error_password));
            }else{
                loginUserRequest();
            }
        }else if(v.getId() ==  R.id.txt_forget){
            navController.navigate(R.id.action_loginFragment_to_forgetPasswordFragment);
        }
    }

    private Observer<Event<BaseResponse<User.CurrentUser>>> observer = loginResponseBaseResponse -> {

        if(loginResponseBaseResponse.getContentIfNotHandled() != null) {

            if (loginResponseBaseResponse.peekContent().isStatus()) {
                authViewModel.setActivityChange();
            } else {
                binding.loadingLayout.setVisibility(View.GONE);
                Toast.makeText(getContext(), loginResponseBaseResponse.peekContent().getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    };

    private void loginUserRequest(){
        binding.loadingLayout.setVisibility(View.VISIBLE);
        loginViewModel.requestLogin();
    }

}
