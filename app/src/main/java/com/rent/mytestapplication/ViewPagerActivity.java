package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPagerFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("This is title");
//        toolbar.setSubtitle("This is subtitle");
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        setSupportActionBar(toolbar);

        ((NestedScrollView) this.findViewById(R.id.scroll_view)).setFillViewport(true);

        mFragment = new ViewPagerFragment() {

            @Override
            protected void initConfig(Config config) {
                config.pageTitles = new String[] { "标签1", "标签2", "标签3", "标签4" };
                config.fragments = new Fragment[] {
                        new TestFragment("this is 标签1"),
                        new TestListFragment("this is 标签2"),
                        new TestFragment("this is 标签3"),
                        new TestListFragment("this is 标签4")
                };
            }
        };
        getSupportFragmentManager().beginTransaction().add(R.id.scroll_view, mFragment).commitAllowingStateLoss();
    }
}
