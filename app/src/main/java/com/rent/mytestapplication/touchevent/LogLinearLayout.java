package com.rent.mytestapplication.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by RenTao on 16/12/10.
 */

public class LogLinearLayout extends LinearLayout {
    public LogLinearLayout(Context context) {
        super(context);
        initListener();
    }

    public LogLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initListener();
    }

    public LogLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initListener();
    }

    private void initListener() {
        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_MOVE)
                    Log.d("TouchEvent", "ViewGroup onTouch, action is " + event.getAction());
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ViewGroup onTouchEvent, action is " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ViewGroup onInterceptTouchEvent, action is " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "ViewGroup dispatchTouchEvent, action is " + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
