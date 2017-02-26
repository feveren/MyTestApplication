package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class PagerFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_fragment_activity);

        getSupportFragmentManager().beginTransaction().add(R.id.activity_pager_fragment_activity, new ViewPagerFragment() {

            @Override
            protected void initConfig(Config config) {
                config.pageTitles = new String[] { "标签1", "标签2", "标签3", "标签4", "标签5", "标签6" };
                config.tabScrollable = true;
                config.fragments = new Fragment[] {
                        new TestPagerFragment("this is 标签1"),
                        new TestPagerFragment("this is 标签2"),
                        new TestPagerFragment("this is 标签3"),
                        new TestPagerFragment("this is 标签4"),
                        new TestPagerFragment("this is 标签5"),
                        new TestPagerFragment("this is 标签6")
                };
            }
        }).commitAllowingStateLoss();
    }

}
