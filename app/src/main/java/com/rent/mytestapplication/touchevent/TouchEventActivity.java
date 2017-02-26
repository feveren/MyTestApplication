package com.rent.mytestapplication.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.rent.mytestapplication.R;

public class TouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "Activity onTouchEvent, action is " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("TouchEvent", "------------------------");
        }
        if (event.getAction() != MotionEvent.ACTION_MOVE)
            Log.d("TouchEvent", "Activity dispatchTouchEvent, action is " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    public void toastMsg(View v) {
        Toast.makeText(this, "View clicked", Toast.LENGTH_SHORT).show();
    }
}
