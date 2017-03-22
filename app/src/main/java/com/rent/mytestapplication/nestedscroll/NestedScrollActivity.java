package com.rent.mytestapplication.nestedscroll;

import android.os.Bundle;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;

import com.rent.mytestapplication.R;

public class NestedScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll);

        NestedScrollingChild child;
        NestedScrollingChildHelper childHelper;

        NestedScrollingParent parent;
        NestedScrollingParentHelper parentHelper;

        NestedScrollView scrollView;
    }
}
