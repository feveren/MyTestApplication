package com.rent.mytestapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/1.
 */

public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        print("onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        print("onBind");
        return new BinderImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        print("onStartCommand, flags: " + flags + ", startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        print("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        print("onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        print("onDestroy");
    }

    private void print(String msg) {
        Log.i("TestService", getClass().getSimpleName() + ": " + msg);
    }

    public interface ITestBinder {
        void print(String msg);
    }

    class BinderImpl extends Binder implements ITestBinder {

        @Override
        public void print(String msg) {
            print(msg);
        }
    }
}
