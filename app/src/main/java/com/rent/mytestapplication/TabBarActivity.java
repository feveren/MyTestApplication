package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rent.mytestapplication.widget.CommonTabLayout;

public class TabBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);

        CommonTabLayout tabBar1 = (CommonTabLayout) findViewById(R.id.tab_bar1);
        tabBar1.setTabs(new String[] { "tab1", "tab2", "tab3", "tab4" });

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabBar1.setupViewPager(viewPager);
        Fragment[] fragments = new Fragment[] {
                new TestPagerFragment("this is 标签1"),
                new TestPagerFragment("this is 标签2"),
                new TestPagerFragment("this is 标签3"),
                new TestPagerFragment("this is 标签4")
        };
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(1);
    }
}
