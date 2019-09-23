package com.example.car.function.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.view.menu.MenuBuilder;

import com.example.car.R;
import com.example.car.base.BaseFragment;


public class LoginFragment extends BaseFragment implements LoginContract.View {
    private LoginContract.Presenter mPresenter;
//    private LoginFragmentBinding mLoginFragmentBinding;
    private LoginViewModel mLoginViewModel;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (mLoginFragmentBinding == null) {
//            mLoginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false);
//        }
//        mLoginFragmentBinding.setViewModel(mLoginViewModel);
//        mLoginFragmentBinding.setActionHandler(mPresenter);
//        setHasOptionsMenu(true);
//        return mLoginFragmentBinding.getRoot();
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        if (menu instanceof MenuBuilder) {
//            ((MenuBuilder) menu).setOptionalIconsVisible(true);
//        }
//        inflater.inflate(R.menu.fragment_login_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_settings_url:
//                UrlSettingsActivity.start(getActivity());
//                return true;
//        }
        return false;
    }

    public void setViewModel(LoginViewModel viewModel) {
        this.mLoginViewModel = viewModel;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void gotoMainActivity() {
//        MainActivity.start(getActivity());
//        getActivity().finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
