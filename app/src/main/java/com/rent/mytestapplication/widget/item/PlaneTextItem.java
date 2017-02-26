package com.rent.mytestapplication.widget.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.utils.TypeArrayUtil;

/**
 * @attr ref R.styleable#PlaneTextItem_label
 * @attr ref R.styleable#PlaneTextItem_android_text
 * @attr ref R.styleable#PlaneTextItem_borderEnabled
 * Created by RenTao on 2016/9/21.
 */
public class PlaneTextItem extends RelativeLayout {
    private String mKey;
    private TextView mTextLabel, mTextValue;

    public PlaneTextItem(Context context) {
        this(context, null);
    }

    public PlaneTextItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaneTextItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlaneTextItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public PlaneTextItem(Context context, CharSequence label, String key, CharSequence value) {
        this(context);

        this.mKey = key;
        mTextLabel.setText(label);
        setText(value);
    }

    private void init(AttributeSet attrs) {
        if (getLayoutParams() == null) {
            setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, 100));
        }

        // parse attrs
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PlaneTextItem);
        String label = TypeArrayUtil.getString(getContext(), typedArray, R.styleable.PlaneTextItem_label);
        String text = TypeArrayUtil.getString(getContext(), typedArray, R.styleable.PlaneTextItem_android_text);
        boolean borderEnabled = typedArray.getBoolean(R.styleable.PlaneTextItem_borderEnabled, false);
        typedArray.recycle();

        // find views
        inflate(getContext(), R.layout.merge_plane_text_item, this);
        mTextLabel = (TextView) findViewById(R.id.text_label);
        mTextValue = (TextView) findViewById(R.id.text_value);

        // init views
        mTextLabel.setText(label);
        setText(text);
        if (borderEnabled) {
            findViewById(R.id.line_top).setVisibility(View.VISIBLE);
            findViewById(R.id.line_bottom).setVisibility(View.VISIBLE);
        }
    }

    public void setText(CharSequence text) {
        mTextValue.setText(text);
    }

    public void setText(String key, CharSequence value) {
        this.mKey = key;
        setText(value);
    }

    public String getKey() {
        return mKey;
    }

    public TextView getValue() {
        return mTextValue;
    }
}
