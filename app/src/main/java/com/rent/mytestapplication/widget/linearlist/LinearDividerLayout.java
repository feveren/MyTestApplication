package com.rent.mytestapplication.widget.linearlist;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.utils.TypeArrayUtil;

/**
 * 带分割线的Layout，只能垂直布局
 * @attr ref android.R.styleable#LinearDividerLayout_android_dividerHeight
 * @attr ref android.R.styleable#LinearDividerLayout_android_divider
 * @attr ref android.R.styleable#LinearDividerLayout_android_headerDividersEnabled
 * @attr ref android.R.styleable#LinearDividerLayout_android_footerDividersEnabled
 * @attr ref R.styleable#LinearDividerLayout_dividerMarginLeft
 * @attr ref R.styleable#LinearDividerLayout_dividerMarginRight
 * Created by RenTao on 2016/9/17.
 */
public class LinearDividerLayout extends ViewGroup {
    private int mDividerHeight, mDividerColor;
    private float mDividerHeightHalf;
    private int mDividerMarginLeft, mDividerMarginRight;
    private boolean mHeaderDividersEnabled, mFooterDividersEnabled;
    private Paint mDividerPaint;
    private LinearAdapter mAdapter;

    public LinearDividerLayout(Context context) {
        this(context, null);
    }

    public LinearDividerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearDividerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LinearDividerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttrs(attrs);
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LinearDividerLayout);
        mDividerHeight = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.LinearDividerLayout_android_dividerHeight, 1);
        mDividerHeightHalf = (float) mDividerHeight / 2;
        mDividerColor = TypeArrayUtil.getColor(getContext(), typedArray, R.styleable.LinearDividerLayout_android_divider, Color.GRAY);
        mDividerMarginLeft = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.LinearDividerLayout_dividerMarginLeft);
        mDividerMarginRight = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.LinearDividerLayout_dividerMarginRight);
        mHeaderDividersEnabled = typedArray.getBoolean(R.styleable.LinearDividerLayout_android_headerDividersEnabled, true);
        mFooterDividersEnabled = typedArray.getBoolean(R.styleable.LinearDividerLayout_android_footerDividersEnabled, true);
        typedArray.recycle();
    }

    public void setAdapter(LinearAdapter adapter) {
        this.mAdapter = adapter;

        if (mAdapter == null || mAdapter.getCount() <= 0) {
            return;
        }
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            addView(mAdapter.getView(i, this));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;
        if (getChildCount() > 0) {
            height += mHeaderDividersEnabled ? mDividerHeight : 0;
            height += mFooterDividersEnabled ? mDividerHeight : 0;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, parentHeightSize);
            height += child.getMeasuredHeight();
            if (i < getChildCount() - 1) {
                height += mDividerHeight;
            }
        }
        setMeasuredDimension(parentWidthSize, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() <= 0) {
            return;
        }
        int top = mHeaderDividersEnabled ? mDividerHeight : 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            MarginLayoutParams params = ((MarginLayoutParams) child.getLayoutParams());
            child.layout(params.leftMargin, top, r, top + child.getMeasuredHeight());
            top += child.getMeasuredHeight() + mDividerHeight;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (getChildCount() <= 0) {
            return;
        }
        if (mDividerPaint == null) {
            mDividerPaint = new Paint();
            mDividerPaint.setColor(mDividerColor);
            mDividerPaint.setStrokeWidth(mDividerHeight);
        }
        if (mHeaderDividersEnabled) {
            canvas.drawLine(0, mDividerHeightHalf, getWidth(), mDividerHeightHalf, mDividerPaint);
        }
        for (int i = 1; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            canvas.drawLine(mDividerMarginLeft, child.getTop() - mDividerHeightHalf, getWidth() - mDividerMarginRight, child.getTop() - mDividerHeightHalf, mDividerPaint);
        }
        if (mFooterDividersEnabled) {
            canvas.drawLine(0, getHeight() - mDividerHeightHalf, getWidth(), getHeight() - mDividerHeightHalf, mDividerPaint);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
