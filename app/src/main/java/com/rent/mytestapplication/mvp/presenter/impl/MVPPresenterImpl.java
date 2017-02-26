package com.rent.mytestapplication.mvp.presenter.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.rent.mytestapplication.common.presenter.impl.BasePresenterImpl;
import com.rent.mytestapplication.mvp.Callback;
import com.rent.mytestapplication.mvp.presenter.MVPPresenter;
import com.rent.mytestapplication.mvp.view.MVPView;

/**
 *
 * Created by RenTao on 16/11/16.
 */
public class MVPPresenterImpl extends BasePresenterImpl<MVPView> implements MVPPresenter {

    @Override
    public void login(String name, String password) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText((Activity) getView(), "username or password is null", Toast.LENGTH_SHORT).show();
            return;
        }
        requestLogin(name, password, new Callback() {

            @Override
            public void onResult(int code, String message) {
                if (code == 0) {
                    getView().onLoginSuccess();
                } else {
                    getView().onLoginFailed();
                }
            }

            @Override
            public void onError(int code) {
                getView().onLoginFailed();
            }
        });
    }

    private void requestLogin(String name, String password, Callback callback) {
        if (name.equals("android") && password.equals("123456")) {
            callback.onResult(0, null);
        } else {
            callback.onError(-1);
        }
    }
}
