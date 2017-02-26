package com.rent.mytestapplication.widget.linearlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rent.mytestapplication.R;

import java.util.List;

/**
 * Created by RenTao on 2016/9/4.
 */
public class CommonLinearAdapter extends LinearAdapter {
    private LayoutInflater mInflater;
    private List<Item> mData;

    public CommonLinearAdapter(Context context, List<Item> data) {
        mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    protected int getCount() {
        return mData.size();
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
//        Item item = mData.get(position);
        View root = mInflater.inflate(R.layout.common_linear_item, parent, false);
//        if (item.resId > 0) {
//            ((ImageView) root.findViewById(R.id.image)).setImageResource(item.resId);
//        } else {
//            root.findViewById(R.id.image).setVisibility(View.GONE);
//        }
//        if (item.title != null && !item.title.equals("")) {
//            ((TextView) root.findViewById(R.id.text_title)).setText(item.title);
//        }
//        if (item.subtitle != null && !item.subtitle.equals("")) {
//            TextView subtitle = (TextView) root.findViewById(R.id.text_subtitle);
//            subtitle.setText(item.subtitle);
//            subtitle.setVisibility(View.VISIBLE);
//        }
//        if (item.hasBadge) {
//            View badge = root.findViewById(R.id.badge);
//            badge.setVisibility(View.VISIBLE);
//        }
        return root;
    }

    public static class Item {
        int resId;
        String title;
        String subtitle;
        boolean hasBadge = false;

        public Item(String title) {
            this.title = title;
        }

        public Item(int resId, String title) {
            this.resId = resId;
            this.title = title;
        }

        public Item(int resId, String title, String subtitle) {
            this.resId = resId;
            this.title = title;
            this.subtitle = subtitle;
        }

        public Item(int resId, String title, String subtitle, boolean hasBadge) {
            this.resId = resId;
            this.title = title;
            this.subtitle = subtitle;
            this.hasBadge = hasBadge;
        }
    }
}
