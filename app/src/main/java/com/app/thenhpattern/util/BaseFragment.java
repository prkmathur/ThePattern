package com.app.thenhpattern.util;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.thenhpattern.view.MainActivity;
import javax.inject.Inject;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    private ViewModel viewModel;
    private ViewModel parentViewModel;
    private NavController navController;
    private ViewDataBinding viewDataBinding;
    private Toolbar toolbar;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public BaseFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(setViewModelClass()!=null) {
            viewModel = ViewModelProviders.of(getActivity(),viewModelFactory).get(setViewModelClass());
        }

        if(setParentViewModelClass()!=null) {
            parentViewModel = ViewModelProviders.of(this.getActivity()).get(setParentViewModelClass());
        }

        // This callback will only be called when MyFragment is at least Started.

        if(getBackCallback() != null){
            requireActivity().getOnBackPressedDispatcher().addCallback(this, getBackCallback());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.inflate(inflater,setContentLayout(), container, false);

        return viewDataBinding.getRoot();

    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            navController = Navigation.findNavController(view);
        }catch (Exception e){
            Log.d("BaseFragment","view does not have a NavController set");
        }

        if(getActivity() instanceof MainActivity) {
            if (((MainActivity) getActivity()).getToolbar() != null) {
                toolbar = ((MainActivity) getActivity()).getToolbar();
                if (toolbar != null) {
                    initHeaderAction();
                }
            }
        }

    }

    private void initHeaderAction(){

        if(getToolbar() != null) {

            if (getToolbar().getTitle() != null && getToolbar().getTitle().trim().length() != 0) {
                toolbar.setTitle(getToolbar().getTitle());
            }

            if(getToolbar().getLeftClickListener() != null){
                toolbar.setLeftClickListener(getToolbar().getLeftClickListener());
            }

            if(getToolbar().getRightClickListener() != null){
                toolbar.setRightClickListener(getToolbar().getRightClickListener());
            }

            if(getToolbar().getFragmentHasLeft() != null){
                toolbar.setHasLeft(getToolbar().getFragmentHasLeft());
            }

            if(getToolbar().getFragmentHasRight() != null){
                toolbar.setHasRight(getToolbar().getFragmentHasRight());
            }

            if(getToolbar().getFragmentHasRight() != null){
                toolbar.setHasRight(getToolbar().getFragmentHasRight());
            }

            if(getToolbar().getRequired() != null){
                toolbar.setMainVisibility(getToolbar().getRequired());
            }

        }
    }

    public abstract int setContentLayout();

    public abstract Class setViewModelClass();

    public abstract Class setParentViewModelClass();

    public abstract void setObservers();

    public abstract void removeObservers();

    public abstract Toolbar getToolbar();

    public OnBackPressedCallback getBackCallback(){
        return null;
    }

    public ViewModel getViewModel(){ return viewModel; }

    public ViewModel getParentViewModel(){ return parentViewModel; }

    public NavController getNavController(){ return navController; }

    public ViewDataBinding getBinding(){ return viewDataBinding; }

}
