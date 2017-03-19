package com.rent.mytestapplication.retrofit.observer;

import android.app.Activity;
import android.app.ProgressDialog;

import com.rent.mytestapplication.retrofit.observer.event.RequestEvent;

import java.lang.ref.WeakReference;

/**
 *
 * Created by RenTao on 17/3/19.
 */
public class DialogObserver<T> extends ResponseObserver<T> {

    public DialogObserver(Activity context, String msg) {
        addEvent(new Event<T>(context, msg));
    }

    private static class Event<T> extends RequestEvent<T> {
        private WeakReference<Activity> mContext;
        private String mLoadingMessage;
        private ProgressDialog mLoadingDialog;

        Event(Activity context, String msg) {
            this.mContext = new WeakReference<>(context);
            mLoadingMessage = msg;
        }

        @Override
        public void onPreRequest() {
            showDialog();
        }

        @Override
        public void onFinish() {
            dismissDialog();
        }

        private boolean isActivityFinished() {
            return mContext == null || mContext.get() == null || mContext.get().isFinishing();
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
    }
}
