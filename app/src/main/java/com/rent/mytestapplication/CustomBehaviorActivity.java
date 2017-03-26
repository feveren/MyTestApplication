package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class CustomBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);

        this.findViewById(R.id.dependency).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int y = (int) event.getRawY() - v.getMeasuredHeight() / 2;
                        v.layout(v.getLeft(), y, v.getRight(), y + v.getMeasuredHeight());
                        System.out.println("y: " + y + ", getMeasuredHeight: " + v.getMeasuredHeight());
                        break;
                }
                return true;
            }
        });
    }
}
