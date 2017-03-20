package com.rent.mytestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rent.mytestapplication.activityoptions.ActOptsActivity;
import com.rent.mytestapplication.aidl.AidlActivity;
import com.rent.mytestapplication.anim.BallFallingActivity;
import com.rent.mytestapplication.anim.VectorActivity;
import com.rent.mytestapplication.mvp.MVPActivity;
import com.rent.mytestapplication.retrofit.RetrofitActivity;
import com.rent.mytestapplication.touchevent.NestedScrollActivity;
import com.rent.mytestapplication.touchevent.TouchEventActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nestedScrollView(View v) {
        startActivity(new Intent(this, NestedScrollViewActivity.class));
    }

    public void customBehavior(View v) {
        startActivity(new Intent(this, CustomBehaviorActivity.class));
    }

    public void anim(View v) {
        startActivity(new Intent(this, AnimActivity.class));
    }

    public void butterKnife(View v) {
        startActivity(new Intent(this, ButterKnifeActivity.class));
    }

    public void viewPagerActivity(View v) {
        startActivity(new Intent(this, ViewPagerActivity.class));
    }

    public void stableIDsActivity(View v) {
        startActivity(new Intent(this, StableIDsActivity.class));
    }

    public void linearListView(View v) {
        startActivity(new Intent(this, LinearListViewActivity.class));
    }

    public void fragmentSwitcher(View v) {
        startActivity(new Intent(this, FragmentSwitcherActivity.class));
    }

    public void pagerFragment(View v) {
        startActivity(new Intent(this, PagerFragmentActivity.class));
    }

    public void tabBarActivity(View v) {
        startActivity(new Intent(this, TabBarActivity.class));
    }

    public void testMVP(View v) {
        startActivity(new Intent(this, MVPActivity.class));
    }

    public void touchEvent(View v) {
        startActivity(new Intent(this, TouchEventActivity.class));
    }

    public void nestedScroll(View v) {
        startActivity(new Intent(this, NestedScrollActivity.class));
    }

    public void aidl(View v) {
        startActivity(new Intent(this, AidlActivity.class));
    }

    public void retrofit(View v) {
        startActivity(new Intent(this, RetrofitActivity.class));
    }

    public void rxLifecycle(View v) {
        startActivity(new Intent(this, RxLifecycleActivity.class));
    }

    public void ballFalling(View v) {
        startActivity(new Intent(this, BallFallingActivity.class));
    }

    public void vectorDrawable(View v) {
        startActivity(new Intent(this, VectorActivity.class));
    }

    public void actOpts(View v) {
        startActivity(new Intent(this, ActOptsActivity.class));
    }

    long[] mClicks = new long[5];

    public void testClick(View v) {
        for (int i = 0; i < mClicks.length; i++) {
            System.out.print(mClicks[i] + " ");
        }
        System.out.println();
        System.arraycopy(mClicks, 1, mClicks, 0, mClicks.length - 1);
        mClicks[mClicks.length - 1] = SystemClock.uptimeMillis();
        if (mClicks[0] >= (SystemClock.uptimeMillis() - 500)) {
            System.out.println("clicked 5 times");
            Toast.makeText(this, "click 5 times", Toast.LENGTH_SHORT).show();
        }
    }

    public void showBottomSheet(View v) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        textView.setGravity(Gravity.CENTER);
        textView.setText("this is BottomSheetDialog");
        dialog.setContentView(textView);
//        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
