package com.rent.mytestapplication.retrofit.observer;

import com.rent.mytestapplication.retrofit.bean.Result;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <p> 执行顺序
 * <p> 1. {@link #onPrepare()}
 * <p> 2. {@link #onSuccess(T)} / {@link #onFailure(int, String)}
 * <p> 3. {@link #onFinish()}
 * Created by RenTao on 17/1/13.
 */
public class ResponseObserver<T> implements Observer<Result<T>> {
    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        onPrepare();
    }

    @Override
    public void onNext(Result<T> result) {
        if (result.code == 0) {
            onSuccess(result.data);
        } else {
            onFailure(result.code, result.message);
        }
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFailure(-1, "");
        onFinish();
    }

    @Override
    public void onComplete() {}

    public Disposable getDisposable() {
        return mDisposable;
    }

    protected void onPrepare() {}

    protected void onSuccess(T data) {}

    protected void onFailure(int code, String message) {}

    protected void onFinish() {}
}
