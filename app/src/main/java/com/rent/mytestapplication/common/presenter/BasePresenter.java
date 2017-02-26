package com.rent.mytestapplication.common.presenter;

import com.rent.mytestapplication.common.view.BaseView;

/**
 *
 * Created by RenTao on 17/1/17.
 */
public interface BasePresenter<T extends BaseView> {

    void bindView(T view);

    T getView();

    void clear();
}
