package com.rent.mytestapplication.widget.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.utils.TypeArrayUtil;

/**
 *
 * Created by RenTao on 16/9/27.
 */
public class CommonLinearItem extends RelativeLayout {
    private ImageView mImage;
    private TextView mTextLabel;
    private TextView mTextDesc;
    private View mBadge;

    public CommonLinearItem(Context context) {
        this(context, null);
    }

    public CommonLinearItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonLinearItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommonLinearItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (getLayoutParams() == null) {
            setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, 100));
        }

        // parse attrs
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CommonLinearItem);
        int icon = typedArray.getResourceId(R.styleable.CommonLinearItem_icon, 0);
        String label = TypeArrayUtil.getString(getContext(), typedArray, R.styleable.CommonLinearItem_label);
        String desc = TypeArrayUtil.getString(getContext(), typedArray, R.styleable.CommonLinearItem_desc);
        typedArray.recycle();

        // find views
        inflate(getContext(), R.layout.common_linear_item, this);
        mImage = (ImageView) findViewById(R.id.image);
        mTextLabel = (TextView) findViewById(R.id.text_label);
        mTextDesc = (TextView) findViewById(R.id.text_desc);
        mBadge = findViewById(R.id.badge);

        // init views
        setIcon(icon);
        setLabel(label);
        setDesc(desc);
    }

    public void setIcon(int resId) {
        if (resId > 0) {
            mImage.setImageResource(resId);
            mImage.setVisibility(View.VISIBLE);
        } else {
            mImage.setVisibility(View.GONE);
        }
    }

    public void setLabel(String text) {
        mTextLabel.setText(text);
    }

    public void setDesc(String text) {
        if (text != null && !text.trim().equals("")) {
            mTextDesc.setText(text);
            mTextDesc.setVisibility(View.VISIBLE);
        } else {
            mTextDesc.setVisibility(View.GONE);
        }
    }
}
