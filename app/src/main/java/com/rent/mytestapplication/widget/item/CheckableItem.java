package com.rent.mytestapplication.widget.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.utils.TypeArrayUtil;

/**
 *
 * Created by RenTao on 2016/9/15.
 */
public class CheckableItem extends RelativeLayout implements View.OnClickListener {
    private TextView mTextView;
    private View mCheckbox;

    public CheckableItem(Context context) {
        this(context, null);
    }

    public CheckableItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CheckableItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public CheckableItem(Context context, CharSequence text, boolean isChecked) {
        this(context, null);

        setText(text);
        setChecked(isChecked);
    }

    private void init(AttributeSet attrs) {
        if (getLayoutParams() == null) {
            setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, 80));
        }

        // parse attrs
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CheckableItem);
        String text = TypeArrayUtil.getString(getContext(), typedArray, R.styleable.CheckableItem_android_text);
        boolean isChecked = typedArray.getBoolean(R.styleable.CheckableItem_checked, false);
        typedArray.recycle();

        // find views
        inflate(getContext(), R.layout.merge_checkable_item, this);
        mTextView = (TextView) findViewById(R.id.text);
        mCheckbox = findViewById(R.id.checkbox);

        // init views
        setText(text);
        setChecked(isChecked);
        setOnClickListener(this);
    }

    public void setChecked(boolean checked) {
        setCheckedState(checked);
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, checked);
        }
    }

    /**
     * 只对选中状态进行改变，而不会触发回调OnCheckedChangeListener
     */
    public void setCheckedState(boolean checked) {
        mCheckbox.setVisibility(checked ? View.VISIBLE : View.GONE);
    }

    public boolean isChecked() {
        return mCheckbox.isShown();
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    @Override
    public void onClick(View v) {
        setChecked(!isChecked());
    }

    public interface OnCheckedChangeListener {

        void onCheckedChanged(CheckableItem view, boolean isChecked);
    }

    private OnCheckedChangeListener mOnCheckedChangeListener;

    public void setOnCheckedChangeListener(OnCheckedChangeListener l) {
        this.mOnCheckedChangeListener = l;
    }
}
