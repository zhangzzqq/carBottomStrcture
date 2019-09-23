package com.example.car.function.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;

import com.example.car.R;
import com.example.car.base.AppBaseActivity;

public class LoginActivity extends AppBaseActivity {
//    private LoginActivityBinding binding;
    private LoginPresenter loginPresenter;

    public static final void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
//        setupToolbar();
//
//        LoginFragment tasksFragment =
//                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if (tasksFragment == null) {
//            tasksFragment = LoginFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(
//                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
//        }
//        tasksFragment.setViewModel(new LoginViewModel());
//        loginPresenter = new LoginPresenter(tasksFragment);
    }

    private void setupToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle(R.string.title_activity_login);
    }

}

