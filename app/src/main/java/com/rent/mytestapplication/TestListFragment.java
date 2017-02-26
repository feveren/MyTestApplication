package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class TestListFragment extends Fragment {

    private String mText;

    public TestListFragment(String text) {
        this.mText = text;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(mText + ", index " + i);
        }
        listView.setAdapter(new ListAdapter(list));
        return listView;
    }

    private class ListAdapter extends BaseAdapter {
        private List<String> mData;

        public ListAdapter(List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_item, parent, false);
                TextView text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(text);
            }
            ((TextView) convertView.getTag()).setText(mData.get(position));
            return convertView;
        }
    }

}
