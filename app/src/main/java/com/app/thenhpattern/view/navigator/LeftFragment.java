package com.app.thenhpattern.view.navigator;

import android.view.View;
import com.app.thenhpattern.R;
import com.app.thenhpattern.util.BaseFragment;
import com.app.thenhpattern.util.Toolbar;
import com.app.thenhpattern.viewmodel.navigator.LeftViewModel;

public class LeftFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public int setContentLayout() {
        return R.layout.fragment_left;
    }

    @Override
    public Class setViewModelClass() {
        return LeftViewModel.class;
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

}
