package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 * Created by RenT on 2016/8/22.
 */
public abstract class ViewPagerFragment extends Fragment {
    private Config mConfig;
    private TabLayout mTabs;
    private ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConfig = new Config();
        initConfig(mConfig);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_pager, container, false);
        buildTab(root);
        buildViewPager(root);
        return root;
    }

    private TabLayout buildTab(View root) {
        mTabs = (TabLayout) root.findViewById(R.id.tabs);
        mTabs.getLayoutParams().height = mConfig.tabHeight;

        // config tabs
        if (mConfig.tabScrollable) {
            mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            mTabs.setTabMode(TabLayout.MODE_FIXED);
            mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        }
        // add tabs
        for (int i = 0; i < mConfig.pageTitles.length; i++) {
            mTabs.addTab(mTabs.newTab().setCustomView(buildTab(i)));
        }
        // config indicator color and height
        if (mConfig.tabIndicatorColor > 0) {
            mTabs.setSelectedTabIndicatorColor(mConfig.tabIndicatorColor);
        }
        mTabs.setSelectedTabIndicatorHeight(Math.round(getResources().getDisplayMetrics().density * 1));
        return mTabs;
    }

    /**
     * 假如需要自定义Tab的View，重写该方法即可
     */
    protected View buildTab(int index) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_item, mTabs, false);
        ((TextView) view.findViewById(R.id.text)).setText(mConfig.pageTitles[index]);
        return view;
    }

    private ViewPager buildViewPager(View root) {
        mViewPager = (ViewPager) root.findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(mConfig.offscreenPageLimit);
        if (mConfig.onPageChangeListener != null) {
            mViewPager.addOnPageChangeListener(mConfig.onPageChangeListener);
        }
        mViewPager.setAdapter(new ViewPagerFragmentAdapter(getFragmentManager(), mConfig.fragments));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        if (mOnPageChangeListener != null) {
            mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        }
//        mTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.setCurrentItem(mConfig.defaultSelected);
        return mViewPager;
    }

    public int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener l) {
        this.mOnPageChangeListener = l;
    }

    protected abstract void initConfig(Config config);

    public static class Config {
        // Tabs config
        public String[] pageTitles;
        public int tabHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int tabIndicatorColor;
        public boolean tabScrollable = false;

        // ViewPager config
        public Fragment[] fragments;
        public int offscreenPageLimit = 1;
        public int defaultSelected = 0;
        public ViewPager.OnPageChangeListener onPageChangeListener;
    }
}
