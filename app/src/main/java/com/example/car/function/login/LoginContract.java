package com.example.car.function.login;


import com.example.car.base.BasePresenter;
import com.example.car.base.BaseView;

/**
 * Created by Harrison.Pan on 2017/4/20.
 */

public class LoginContract {
    public interface View extends BaseView<Presenter> {
        boolean isActive();

        void gotoMainActivity();
    }

    public interface Presenter extends BasePresenter {
        void start();

        void login(String username, String password);
    }
}
