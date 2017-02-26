package com.rent.mytestapplication.widget.linearlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.rent.mytestapplication.widget.item.CheckableItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by RenTao on 2016/9/15.
 */
public class CheckedItemAdapter extends LinearAdapter implements CheckableItem.OnCheckedChangeListener {
    private Context mContext;
    private List<CheckedItemAdapter.Item> mData;
    private List<CheckableItem> mViews;
    private String mDefaultChecked;

    public CheckedItemAdapter(Context context, List<Item> data) {
        this.mContext = context;
        this.mData = data;
        if (mData != null) {
            mViews = new ArrayList<>(mData.size());
        }
    }

    @Override
    protected int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
        Item item = mData.get(position);
        CheckableItem view = new CheckableItem(mContext);
        view.setText(item.value);
        view.setChecked(item.key.equals(mDefaultChecked));
        view.setOnCheckedChangeListener(this);
        mViews.add(view);
        return view;
    }

    @Override
    public void onCheckedChanged(CheckableItem view, boolean isChecked) {
        if (isChecked) {
            for (CheckableItem checkableItem : mViews) {
                checkableItem.setCheckedState(false);
            }
        }
        view.setCheckedState(isChecked);
    }

    public void setChecked(String key, boolean checked) {
        if (mViews != null && mViews.size() > 0) {
            for (int i = 0; i < mData.size(); i++) {
                if (checked) {
                    mViews.get(i).setChecked(false);
                }
                if (mData.get(i).key.equals(key)) {
                    mViews.get(i).setChecked(checked);
                }
            }
        } else {
            this.mDefaultChecked = key;
        }
    }

    public String getCheckedKey() {
        Item item = getCheckedItem();
        return item == null ? null : item.key;
    }

    public Item getCheckedItem() {
        if (mViews != null && mData != null) {
            for (int i = 0; i < mViews.size(); i++) {
                if (mViews.get(i).isChecked()) {
                    return mData.get(i);
                }
            }
        }
        return null;
    }

    public static class Item {
        public String key;
        public String value;

        public Item() {}

        public Item(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
