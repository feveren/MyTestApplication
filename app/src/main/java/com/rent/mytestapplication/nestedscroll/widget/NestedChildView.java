package com.rent.mytestapplication.nestedscroll.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/23.
 */

public class NestedChildView extends View implements NestedScrollingChild {

    public NestedChildView(Context context) {
        super(context);
    }

    public NestedChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];
    private float lastY;

    @Override
    public boolean isNestedScrollingEnabled() {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;

            case MotionEvent.ACTION_MOVE:
                System.out.println("getY: " + event.getY() + ", lastY: " + lastY + ", consumed[1]: " + consumed[1] + ", offsetInWindow[1]: " + offsetInWindow[1]);
                float scrollY = event.getY() - lastY;
                float max = ((ViewGroup) getParent()).getMeasuredHeight() - getMeasuredHeight();
                if (dispatchNestedPreScroll(0, (int) scrollY, consumed, offsetInWindow)) {
                    scrollY -= consumed[1];
                }
                float scrollTo = getY() + scrollY;
                if (scrollTo <= 0) {
                    scrollTo = 0;
                }
                else if (scrollTo >= max) {
                    scrollTo = max;
                }
                setY(scrollTo);
                break;

            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;
    }
}
