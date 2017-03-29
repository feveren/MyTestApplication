package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

/**
 * https://github.com/Ashok-Varma/BottomNavigation
 */
public class BottomNavigationBarActivity extends AppCompatActivity {
    private BottomNavigationBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);

        // 各模式展示图
        // https://raw.githubusercontent.com/Ashok-Varma/BottomNavigation/master/all.gif

        mBottomBar = (BottomNavigationBar) this.findViewById(R.id.bottom_bar);
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "home")
                // 设置红点
//            .setBadgeItem(new BadgeItem().setText("1"))
                // 自定义未选中图片
//                .setInactiveIconResource(resId)
        );
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "music"));
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "find"));
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "book"));
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "favorite"));

        // MODE_FIXED为正常样式；MODE_SHIFTING是滚动带动画的效果

        // BACKGROUND_STYLE_STATIC时ActiveColor是文字选中颜色，BarBackgroundColor是背景色
        mBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomBar.setActiveColor(android.R.color.holo_orange_light);

        // BACKGROUND_STYLE_RIPPLE与上面相反
        // 感觉这是因为RIPPLE可以实现不同的标签显示不同的背景色，但BottomNavigationItem只能设置ActiveColor，而不能设置BarBackgroundColor是背景色
        // 所以才会如此设计
//        mBottomBar.setMode(BottomNavigationBar.MODE_SHIFTING);
//        mBottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
//        mBottomBar.setActiveColor(android.R.color.darker_gray);
//        mBottomBar.setBarBackgroundColor(android.R.color.holo_orange_light);

        mBottomBar.setFirstSelectedPosition(0);
        mBottomBar.initialise();
    }
}
