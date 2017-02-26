package com.rent.mytestapplication.retrofit.observer;

import com.rent.mytestapplication.retrofit.bean.Result;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 * Created by RenTao on 17/1/13.
 */
public class ResponseObserver<T extends Result> implements Observer<T> {
    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T result) {
        if (result.code == 0) {
            onSuccess(result);
        } else {
//            Toast.makeText(mContext, result.message, Toast.LENGTH_SHORT).show();
        }
    }

    protected void onSuccess(T result) {}

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {}

    public Disposable getDisposable() {
        return mDisposable;
    }
}
