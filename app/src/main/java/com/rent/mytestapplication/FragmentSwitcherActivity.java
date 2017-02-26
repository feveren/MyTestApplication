package com.rent.mytestapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentSwitcherActivity extends AppCompatActivity {
    @BindView(R.id.info) TextView info;
    private FragmentSwitcher mSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_switcher);
        ButterKnife.bind(this);

        mSwitcher = new FragmentSwitcher(getSupportFragmentManager(), R.id.container);
        mSwitcher.setAdapter(new FragmentSwitcher.SwitcherAdapter() {

            @Override
            public Fragment getFragment(int position) {
                return new TestFragment(position + " onSelected");
            }

            @Override
            public void onSelected(int position) {
            }
        });
        mSwitcher.setup();
        setInfo();
    }

    @OnClick({R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4})
    public void onClick(View view) {
        mSwitcher.setCurrentItem(Integer.parseInt(view.getTag() + ""));
        setInfo();
    }

    @OnClick(R.id.release)
    public void release() {
        mSwitcher.release();
        setInfo();
    }

    @OnClick(R.id.release_all)
    public void releaseAll() {
        mSwitcher.releaseAll();
        setInfo();
    }

    private void setInfo() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        info.setText("availMem: " + memoryInfo.availMem
            + "\ntotalMemory: " + memoryInfo.totalMem
            + "\nlowMemory: " + memoryInfo.lowMemory
            + "\nthreshold: " + memoryInfo.threshold
        );
    }
}
