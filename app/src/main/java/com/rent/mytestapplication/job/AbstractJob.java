package com.rent.mytestapplication.job;

import io.reactivex.Observable;

/**
 *
 * Created by aa on 2017/3/6.
 */
public abstract class AbstractJob<T> {
    abstract void execute();

    abstract void onPostExecute(T result);

    private JobCallback mCallback;

    public void setCallback(JobCallback callback) {
        this.mCallback = callback;
    }

    protected void onJobSuccess() {
        if (mCallback != null) {
            mCallback.onSuccess();
        }
    }

    protected void onJobError() {
        if (mCallback != null) {
            mCallback.onError();
        }
    }
}
