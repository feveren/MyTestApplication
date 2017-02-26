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
public class MultiCheckedItemAdapter extends LinearAdapter {
    private Context mContext;
    private List<MultiCheckedItemAdapter.Item> mData;
    private List<CheckableItem> mViews;

    public MultiCheckedItemAdapter(Context context, List<MultiCheckedItemAdapter.Item> data) {
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
        MultiCheckedItemAdapter.Item item = mData.get(position);
        CheckableItem view = new CheckableItem(mContext);
        view.setText(item.value);
        view.setChecked(item.isChecked);
        mViews.add(view);
        return view;
    }

    public void checkAll() {
        for (CheckableItem item : mViews) {
            item.setChecked(true);
        }
    }

    public void setChecked(String key, boolean checked) {
        if (mViews != null && mViews.size() > 0) {
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).key.equals(key)) {
                    mViews.get(i).setChecked(checked);
                    break;
                }
            }
        }
    }

    public List<String> getCheckedKeys() {
        List<MultiCheckedItemAdapter.Item> list = getCheckedItems();
        if (list != null) {
            List<String> keys = new ArrayList<>(list.size());
            for (MultiCheckedItemAdapter.Item item : list) {
                keys.add(item.key);
            }
            return keys;
        }
        return null;
    }

    public List<MultiCheckedItemAdapter.Item> getCheckedItems() {
        if (mViews != null && mData != null) {
            List<MultiCheckedItemAdapter.Item> list = new ArrayList<>();
            for (int i = 0; i < mViews.size(); i++) {
                if (mViews.get(i).isChecked()) {
                    list.add(mData.get(i));
                }
            }
            return list;
        }
        return null;
    }

    public static class Item {
        public String key;
        public String value;
        public boolean isChecked;

        public Item() {}

        public Item(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Item(String key, String value, boolean isChecked) {
            this.key = key;
            this.value = value;
            this.isChecked = isChecked;
        }
    }
}
