package com.rent.mytestapplication.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by RenTao on 16/12/11.
 */

public class LogScrollView extends ScrollView {
    public LogScrollView(Context context) {
        super(context);
    }

    public LogScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ScrollView onTouchEvent, action is " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ScrollView onInterceptTouchEvent, action is " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ScrollView dispatchTouchEvent, action is " + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
