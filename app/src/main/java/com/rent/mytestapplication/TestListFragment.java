package com.rent.mytestapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private boolean mRecycler;

    public TestListFragment(String text, boolean recycler) {
        this.mText = text;
        this.mRecycler= recycler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(mText + ", index " + i);
        }

        if (mRecycler) {
            RecyclerView recyclerView = new RecyclerView(getContext());
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new RecycleAdapter(getContext(), list));
            return recyclerView;
        } else {
            ListView listView = new ListView(getContext());
            listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            listView.setAdapter(new ListAdapter(list));
            return listView;
        }
    }

    private class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Holder> {
        private List<String> mData;
        private Context mContext;

        RecycleAdapter(Context context, List<String> data) {
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder((LayoutInflater.from(mContext).inflate(R.layout.card_view_item, parent, false)));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.tv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tv;

            Holder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
            }
        }
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
