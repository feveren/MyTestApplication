package com.rent.mytestapplication.retrofit.observer;

import com.rent.mytestapplication.retrofit.bean.Result;
import com.rent.mytestapplication.retrofit.observer.event.RequestEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <p> 执行顺序
 * <p> 1. {@link #onPrepare()}
 * <p> 2. {@link #onSuccess(T)} / {@link #onFailure(int, String)}
 * <p> 3. {@link #onFinish()}
 * Created by RenTao on 17/3/19.
 */
public class ResponseObserver<T extends Result> implements Observer<T> {
    private Disposable mDisposable;

    public ResponseObserver() {
        addEvent(new Event());
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        onEventPreRequest();
    }

    @Override
    public void onNext(T result) {
        onEventPostRequest();
        if (result.code == 0) {
            onEventSuccess(result);
        } else {
            onEventFailure(result.code, result.message);
        }
        onEventFinish();
    }

    @Override
    public void onError(Throwable e) {
        onEventPostRequest();
        e.printStackTrace();
        onEventFailure(-1, "");
        onEventFinish();
    }

    @Override
    public void onComplete() {}

    public Disposable getDisposable() {
        return mDisposable;
    }

    // ---------------- Override by subclass ----------------

    protected void onPrepare() {}

    protected void onSuccess(T data) {}

    protected void onFailure(int code, String message) {}

    protected void onFinish() {}

    // ---------------- Custom RequestEvent ----------------

    private class Event extends RequestEvent<T> {

        @Override
        public void onPreRequest() {
            ResponseObserver.this.onPrepare();
        }

        @Override
        public void onSuccess(T data) {
            ResponseObserver.this.onSuccess(data);
        }

        @Override
        public void onFailure(int code, String message) {
            ResponseObserver.this.onFailure(code, message);

        }

        @Override
        public void onFinish() {
            ResponseObserver.this.onFinish();
        }
    }

    // ---------------- RequestEvent ----------------

    private List<RequestEvent<T>> mEvents = new ArrayList<>(2);

    public void addEvent(RequestEvent<T> event) {
        mEvents.add(event);
    }

    public void onEventPreRequest() {
        for (RequestEvent event : mEvents) {
            event.onPreRequest();
        }
    }

    public void onEventPostRequest() {
        for (RequestEvent event : mEvents) {
            event.onPostRequest();
        }
    }

    public void onEventSuccess(T data) {
        for (RequestEvent<T> event : mEvents) {
            event.onSuccess(data);
        }
    }

    public void onEventFailure(int code, String message) {
        for (RequestEvent event : mEvents) {
            event.onFailure(code, message);
        }
    }

    public void onEventFinish() {
        for (RequestEvent event : mEvents) {
            event.onFinish();
        }
    }
}
