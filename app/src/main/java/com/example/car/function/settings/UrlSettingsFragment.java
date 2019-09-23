package com.example.car.function.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.car.base.BaseFragment;


public class UrlSettingsFragment extends BaseFragment implements UrlSettingsContract.View {
    public static final String KEY_DONE_MENU_ITEM_VISIBLE = "KEY_DONE_MENU_ITEM_VISIBLE";
    private UrlSettingsContract.Presenter mPresenter;
    private MenuItem doneMenuItem;
    private boolean mIsDoneMenuItemVisible = false;

    public static UrlSettingsFragment newInstance() {
        UrlSettingsFragment fragment = new UrlSettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (mBinding == null) {
//            mBinding = UrlSettingsFragmentBinding.inflate(inflater, container, false);
//        }
//        if (savedInstanceState != null) {
//            mIsDoneMenuItemVisible = savedInstanceState.getBoolean(KEY_DONE_MENU_ITEM_VISIBLE, false);
//        }
//        mBinding.setHandler(mPresenter);
//        setHasOptionsMenu(true);
//        setRetainInstance(true);
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.fragment_url_settings_menu, menu);
        return;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        doneMenuItem = menu.findItem(R.id.done);
//        doneMenuItem.setVisible(mIsDoneMenuItemVisible);
//        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (doneMenuItem != null) {
            outState.putBoolean(KEY_DONE_MENU_ITEM_VISIBLE, mIsDoneMenuItemVisible);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setUrl(String url) {
//        mBinding.setUrl(url);
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(getActivity(), resId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDoneMenu(boolean show) {
        if (doneMenuItem != null) {
            doneMenuItem.setVisible(show);
        }
        mIsDoneMenuItemVisible = show;
    }

    @Override
    public void setPresenter(UrlSettingsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
