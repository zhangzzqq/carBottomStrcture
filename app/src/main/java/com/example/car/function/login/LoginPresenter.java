package com.example.car.function.login;

import android.util.Log;

/**
 * Created by Harrison.Pan on 2017/4/20.
 */

public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = LoginPresenter.class.getName();
    private static boolean DEBUG = Log.isLoggable("LoginPresenter", Log.VERBOSE);
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void login(String username, String password) {
        mView.gotoMainActivity();
        if (DEBUG) {
            Log.d(TAG, "login username:" + username + " password:" + password);
        }
    }
}
