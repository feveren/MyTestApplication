package com.rent.mytestapplication.retrofit.observer;

import android.app.Activity;
import android.app.ProgressDialog;

import com.rent.mytestapplication.retrofit.bean.Result;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;

/**
 *
 * Created by RenTao on 17/1/13.
 */
public class DialogObserver<T> extends ResponseObserver<T> {
    private WeakReference<Activity> mContext;
    private String mLoadingMessage;
    private ProgressDialog mLoadingDialog;

    public DialogObserver(Activity context, String msg) {
        this.mContext = new WeakReference<>(context);
        mLoadingMessage = msg;
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        showDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissDialog();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        dismissDialog();
    }

    private void showDialog() {
        if (isActivityFinished()) {
            return;
        }
        if (mLoadingMessage != null && !mLoadingMessage.trim().equals("")) {
            mLoadingDialog = new ProgressDialog(mContext.get());
            mLoadingDialog.setMessage(mLoadingMessage);
            mLoadingDialog.show();
        }
    }

    private void dismissDialog() {
        if (isActivityFinished()) {
            return;
        }
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    private boolean isActivityFinished() {
        return mContext == null || mContext.get() == null || mContext.get().isFinishing();
    }
}
