package com.rent.mytestapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.utils.TypeArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承自TabLayout，增加了一些个性化的功能
 * Created by RenTao on 16/12/1.
 */
public class CommonTabLayout extends TabLayout implements ViewPager.OnPageChangeListener {
    private int mTextSize, mTextSelectedSize;
    private int mTextColor, mTextSelectedColor;
    private int mIndicatorColor, mIndicatorSelectedColor;
    private List<View[]> mViews;

    public CommonTabLayout(Context context) {
        this(context, null);
    }

    public CommonTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.CommonTabLayout);
    }

    public CommonTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDefault();
        parseAttr(attrs);
    }

    public void setTabs(List<String> list) {
        mViews = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            addTab(i, list.get(i));
        }
        onPageSelected(0);
    }

    public void setTabs(String[] arr) {
        mViews = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            addTab(i, arr[i]);
        }
        onPageSelected(0);
    }

    private void initDefault() {
        mTextColor = 0xff000000;
        mTextSize = 14;
    }

    private void parseAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CommonTabLayout);
        mTextSize = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.CommonTabLayout_android_textSize, mTextSize);
        mTextSelectedSize = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.CommonTabLayout_textSelectedSize);

        mTextColor = TypeArrayUtil.getColor(getContext(), typedArray, R.styleable.CommonTabLayout_android_textColor, mTextColor);
        mTextSelectedColor = TypeArrayUtil.getColor(getContext(), typedArray, R.styleable.CommonTabLayout_textSelectedColor);

        mIndicatorColor = TypeArrayUtil.getColor(getContext(), typedArray, R.styleable.CommonTabLayout_indicatorColor);
        mIndicatorSelectedColor = TypeArrayUtil.getColor(getContext(), typedArray, R.styleable.CommonTabLayout_indicatorSelectedColor);
        typedArray.recycle();
    }

    private void addTab(int index, String text) {
        addTab(newTab().setCustomView(buildCustomView(index, text)));
    }

    private View buildCustomView(int index, String text) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_tab_item, this, false);

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setTextColor(mTextColor);
        if (mTextSize > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        textView.setText(text);

        View indicator = view.findViewById(R.id.indicator);
        layoutIndicator(indicator);
        setSelectedTabIndicatorHeight(0);

        if (mIndicatorColor != 0) {
            indicator.setBackgroundColor(mIndicatorColor);
        } else {
            indicator.setVisibility(View.GONE);
        }
        mViews.add(new View[] { textView, indicator });
        return view;
    }

    public void setupViewPager(ViewPager viewPager) {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this));
            viewPager.addOnPageChangeListener(this);
//            addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        }
    }

    private void layoutIndicator(View v) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
        switch (getTabGravity()) {
            case GRAVITY_FILL:
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT, 0);
                layoutParams.addRule(RelativeLayout.ALIGN_RIGHT, 0);
                break;

            case GRAVITY_CENTER:
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.text);
                layoutParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.text);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mViews.size(); i++) {
            TextView textView = (TextView) mViews.get(i)[0];
            View view = mViews.get(i)[1];
            if (mTextSelectedSize != 0 && mTextSelectedSize != mTextSize) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, i == position ? mTextSelectedSize : mTextSize);
            }
            // 假如使用ColorStateList，切换的时候会出现颜色闪烁的情况，所以这里手动setColor
            if (mTextSelectedColor != 0 && mTextSelectedColor != mTextColor) {
                textView.setTextColor(i == position ? mTextSelectedColor : mTextColor);
            }
            if (i == position && mIndicatorSelectedColor != 0) {
                view.setBackgroundColor(mIndicatorSelectedColor);
                view.setVisibility(View.VISIBLE);
            } else {
                if (mIndicatorColor != 0) {
                    view.setBackgroundColor(mIndicatorColor);
                } else {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setTextSize(int size) {
        this.mTextSize = size;
    }

    public void setTextSelectedSize(int size) {
        this.mTextSelectedSize = size;
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
    }

    public void setTextSelectedColor(int color) {
        this.mTextSelectedColor = color;
    }

    public void setIndicatorColor(int color) {
        this.mIndicatorColor = color;
    }

    public void setIndicatorSelectedColor(int color) {
        this.mIndicatorSelectedColor = color;
    }
}
