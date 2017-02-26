package com.rent.mytestapplication.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by RenTao on 16/12/11.
 */

public class LogListView extends ListView {
    private ScrollView mParentScroll;

    public LogListView(Context context) {
        super(context);
        init();
    }

    public LogListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LogListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            requestDisallow(false);
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            requestDisallow(true);
        }
        if (event.getAction() != MotionEvent.ACTION_MOVE) {
            Log.d("TouchEvent", "ListView onTouchEvent, action is " + event.getAction());

        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ListView onInterceptTouchEvent, action is " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ListView dispatchTouchEvent, action is " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    private void requestDisallow(boolean flag) {
        if (mParentScroll == null) {
            ViewGroup group = (ViewGroup) getParent();
            while (group != null && !(group instanceof ScrollView)) {
                group = (ViewGroup) group.getParent();
            }
            if (group != null) {
                mParentScroll = (ScrollView) group;
            }
        }
        if (mParentScroll != null) {
            mParentScroll.requestDisallowInterceptTouchEvent(flag);
        }
    }
}
