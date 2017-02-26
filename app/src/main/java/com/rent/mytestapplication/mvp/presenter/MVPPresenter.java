package com.rent.mytestapplication.mvp.presenter;

import com.rent.mytestapplication.common.presenter.BasePresenter;
import com.rent.mytestapplication.mvp.view.MVPView;

/**
 *
 * Created by RenTao on 16/11/16.
 */
public interface MVPPresenter extends BasePresenter<MVPView> {
    void login(String name, String password);
}
