package com.example.car.function.settings;

import android.util.Log;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by Harrison.Pan on 2017/4/20.
 */

public class UrlSettingsPresenter implements UrlSettingsContract.Presenter {
    private static final String TAG = UrlSettingsPresenter.class.getName();
    private static boolean DEBUG = Log.isLoggable("UrlSettingsPresenter", Log.VERBOSE);
    private UrlSettingsContract.View mView;
    private String mCurrentUrl = "http://center.wiicare.cn";
    private boolean mIsUrlMissing;
    private boolean mIsUrlChanged;

    public UrlSettingsPresenter(UrlSettingsContract.View view, boolean shouldLoadUrl) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mIsUrlMissing = shouldLoadUrl;
    }

    @Override
    public void start() {
        if (mIsUrlMissing) {
            mView.setUrl(mCurrentUrl);
            mIsUrlMissing = false;
        }
    }

    @Override
    public void onUrlTextChanged(CharSequence s) {
        if (DEBUG) {
            Log.d(TAG, "onUrlTextChanged:" + s);
        }
        if (!StringUtils.equals(mCurrentUrl, s.toString())) {
            mView.showDoneMenu(true);
            mIsUrlChanged = true;
        } else {
            mView.showDoneMenu(false);
            mIsUrlMissing = false;
        }
    }

    @Override
    public void test() {
        //// TODO 添加连接测试功能代码
//        mView.showToast(R.string.toast_connect_test_success);
    }

    @Override
    public void saveUrl(String url) {
        mCurrentUrl = url;
        mIsUrlChanged = false;
        // TODO 保存url
    }

    @Override
    public boolean isUrlMissing() {
        return mIsUrlMissing;
    }

    @Override
    public boolean isUrlChanged() {
        return mIsUrlChanged;
    }
}
