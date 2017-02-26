package com.rent.mytestapplication.common.presenter.impl;

import com.rent.mytestapplication.common.presenter.BasePresenter;
import com.rent.mytestapplication.common.view.BaseView;

/**
 *
 * Created by RenTao on 17/1/17.
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
    private T mView;

    @Override
    public void bindView(T view) {
        this.mView = view;
    }

    @Override
    public T getView() {
        return mView;
    }

    @Override
    public void clear() {
        mView = null;
    }
}
