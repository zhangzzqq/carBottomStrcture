package com.example.car.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car.app.ActivityManagerSingle;

/**
 * Created by Harrison.Pan on 30/08/16.
 */
@SuppressLint("Registered")
public class AppBaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        initTheme();
        super.onCreate(savedInstanceState);
        initToolbar();
        ActivityManagerSingle.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        ActivityManagerSingle.getInstance().removeActivity(this);
        super.onDestroy();
    }



    protected void initToolbar() {
    }





    public final void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, null, message);
        } else {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    public final void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    public final void hideSoftInputFromWindow() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        if(focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
