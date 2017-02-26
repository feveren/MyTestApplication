package com.rent.mytestapplication.mvp.view;

import com.rent.mytestapplication.common.view.BaseView;

/**
 *
 * Created by RenTao on 16/11/16.
 */
public interface MVPView extends BaseView {

    void onLoginSuccess();

    void onLoginFailed();
}
