package com.rent.mytestapplication.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 *
 * Created by RenTao on 16/10/10.
 */
public abstract class LazyFragment extends Fragment {

    private boolean mVisibleToUser;
    private boolean mInitialized;
    private boolean mForceRequest;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mVisibleToUser || mForceRequest) {
            System.out.println(this.getClass() + " init from onActivityCreated");
            mInitialized = true;
            requestData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mVisibleToUser = isVisibleToUser;

        if (isAdded() && isVisibleToUser && !mInitialized) {
            System.out.println(this.getClass().getName() + " init from setUserVisibleHint");
            mInitialized = true;
            requestData();
        }
    }

    public abstract void requestData();

    public void setForceRequest(boolean forceRequest) {
        this.mForceRequest = forceRequest;
    }
}
