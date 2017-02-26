package com.rent.mytestapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class AnimActivity extends AppCompatActivity {
    private View mTopSheet;
    private View mTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        mTopSheet = findViewById(R.id.top_sheet);
        mTarget = findViewById(R.id.target);
    }

    public void setPaddingTop(View v) {
        System.out.print("before: " + mTarget.getPaddingTop());
        mTarget.setPadding(mTarget.getPaddingLeft(), mTarget.getPaddingTop() + 50, mTarget.getPaddingRight(), mTarget.getPaddingBottom());
        System.out.println(" after: " + mTarget.getPaddingTop());
    }

    public void setMarginTop(View v) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mTarget.getLayoutParams();
        System.out.print("before: " + layoutParams.topMargin);
        layoutParams.topMargin += 50;
        mTarget.setLayoutParams(layoutParams);
        System.out.println(" after: " + layoutParams.topMargin);
    }

    public void setTranslationY(View v) {
        System.out.print("before: " + mTarget.getTranslationY());
        mTarget.setTranslationY(mTarget.getTranslationY() + 50);
        System.out.println(" after: " + mTarget.getTranslationY());
    }

    public void setY(View v) {
        System.out.print("before: " + mTarget.getY());
        mTarget.setY(mTarget.getY() + 50);
        System.out.println(" after: " + mTarget.getY());
    }

    public void setTop(View v) {
        System.out.print("before: " + mTarget.getTop());
        mTarget.setTop(mTarget.getTop() + 50);
        mTarget.setBottom(mTarget.getBottom() + 50);
        System.out.println(" after: " + mTarget.getTop());
    }

    public void showPosition(View v) {
        System.out.println("MoveSelf y: " + mTarget.getY() + ", TranslationY: " + mTarget.getTranslationY() + ", bottom: " + mTarget.getBottom() + ", top: " + mTarget.getTop() + ", (top - bottom): " + (mTarget.getTop() - mTarget.getBottom()) + ", height: " + mTarget.getHeight());
    }

    public void showOrHide(View v) {
        System.out.println(mTopSheet.getTranslationY());
        if (mTopSheet.getTranslationY() == 0) {
            ObjectAnimator.ofFloat(mTopSheet, "translationY", 0, mTopSheet.getMeasuredHeight()).start();
        } else {
            ObjectAnimator.ofFloat(mTopSheet, "translationY", mTopSheet.getMeasuredHeight(), 0).start();
        }
    }
}
