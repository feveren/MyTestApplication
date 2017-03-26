package com.rent.mytestapplication.nestedscroll.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/3/23.
 */

public class NestedParentView extends LinearLayout implements NestedScrollingParent {

    public NestedParentView(Context context) {
        super(context);
    }

    public NestedParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        System.out.println("getY: " + getY() + ", dy: " + dy);

        float max = ((ViewGroup) getParent()).getMeasuredHeight() - getMeasuredHeight();
        float scrollTo = getY() + dy;
        consumed[0] = 0;
        if (scrollTo <= 0) {
            consumed[1] = (int) getY();
            scrollTo = 0;
        }
        else if (scrollTo >= max) {
            consumed[1] = 0;
            scrollTo = max;
        }
        else {
            consumed[1] = dy;
        }
        setY(scrollTo);
    }
}
