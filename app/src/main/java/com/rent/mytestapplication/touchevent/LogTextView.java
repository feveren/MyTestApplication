package com.rent.mytestapplication.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by RenTao on 16/12/10.
 */

public class LogTextView extends TextView {
    public LogTextView(Context context) {
        super(context);
    }

    public LogTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "View onTouchEvent, action is " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "View dispatchTouchEvent, action is " + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
