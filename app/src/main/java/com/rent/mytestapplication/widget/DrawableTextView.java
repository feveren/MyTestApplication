package com.rent.mytestapplication.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.utils.TypeArrayUtil;

/**
 * 可以设置Drawable宽高的TextView
 *
 * @attr ref R.styleable#DrawableTextView_drawableWidth
 * @attr ref R.styleable#DrawableTextView_drawableHeight
 * Created by RenTao on 2016/9/15.
 */
public class DrawableTextView extends TextView {

    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableTextView);
        int width = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.DrawableTextView_drawableWidth);
        int height = TypeArrayUtil.getDimension(getContext(), typedArray, R.styleable.DrawableTextView_drawableHeight);
        typedArray.recycle();

        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, width, height);
            }
        }
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

}
