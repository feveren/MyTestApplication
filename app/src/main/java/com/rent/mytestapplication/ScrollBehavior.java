package com.rent.mytestapplication;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.dependency;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.layout(child.getLeft(), dependency.getTop(), child.getRight(), dependency.getBottom());
        return true;
    }
}
