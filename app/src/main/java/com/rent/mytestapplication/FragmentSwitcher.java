package com.rent.mytestapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;

/**
 * 用于多个Fragment切换
 * Created by RenTao on 16/9/26.
 */
public class FragmentSwitcher {
    private FragmentManager mFragmentManager;
    private int mLayoutId;
    private SparseArray<Fragment> mFragmentArray;
    private int mCurrentItem = 0;
    private SwitcherAdapter mAdapter;
    private boolean mInitialized;

    public FragmentSwitcher(FragmentManager mFragmentManager, int mLayoutId) {
        this.mLayoutId = mLayoutId;
        this.mFragmentManager = mFragmentManager;
        this.mFragmentArray = new SparseArray<>();
    }

    private void showFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentArray.get(mCurrentItem);
        // add new fragment
        if (fragment == null) {
            fragment = mAdapter.getFragment(mCurrentItem);
            if (fragment != null) {
                transaction.add(mLayoutId, fragment, fragment.getTag() == null ? fragment.getClass().getSimpleName() : fragment.getTag());
                mFragmentArray.put(mCurrentItem, fragment);
            } else {
                Log.e(getClass().getSimpleName(), "index " + mCurrentItem + " is not created, please create it in #getFragment(int)");
                return;
            }
        }
        // show current fragment
        else {
            transaction.show(fragment);
        }
        // hide others
        for (int i = 0; i < mFragmentArray.size(); i++) {
            int key = mFragmentArray.keyAt(i);
            Fragment value = mFragmentArray.get(key);
            if (key != mCurrentItem && value != null) {
                transaction.hide(value);
            }
        }
        transaction.commitAllowingStateLoss();
        // invoke onSelected
        mAdapter.onSelected(mCurrentItem);
    }

    public void setup() {
        if (mInitialized) {
            return;
        }
        if (mAdapter == null) {
            throw new NullPointerException("adapter is null, please invoke #setAdapter(SwitcherAdapter) first");
        }
        mInitialized = true;
        showFragment();
    }

    public void remove(int position) {
        remove(position, null);
    }

    private void remove(int position, FragmentTransaction transaction) {
        Fragment fragment = mFragmentArray.get(position);
        if (fragment == null) {
            return;
        }
        if (transaction == null) {
            mFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
        } else {
            transaction.remove(fragment);
        }
        mFragmentArray.remove(position);
    }

    /**
     * 清理不显示的所有fragment
     */
    public void release() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        int length = mFragmentArray.size();
        for (int i = 0; i < length; i++) {
            int key = mFragmentArray.keyAt(i);
            if (key != mCurrentItem) {
                remove(key, transaction);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 清理所有fragment
     */
    public void releaseAll() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        int length = mFragmentArray.size();
        for (int i = 0; i < length; i++) {
            remove(mFragmentArray.keyAt(i), transaction);
        }
        transaction.commitAllowingStateLoss();
    }

    public void setAdapter(SwitcherAdapter adapter) {
        this.mAdapter = adapter;
    }

    public int getCurrentItem() {
        return mCurrentItem;
    }

    public void setCurrentItem(int position) {
        this.mCurrentItem = position;
        if (mInitialized && mAdapter != null) {
            showFragment();
        }
    }

    interface SwitcherAdapter {

        Fragment getFragment(int position);

        void onSelected(int position);
    }
}
