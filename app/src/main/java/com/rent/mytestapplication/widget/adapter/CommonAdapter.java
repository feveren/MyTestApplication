package com.rent.mytestapplication.widget.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用的Adapter，实现{@link #convert(int, ViewHolder, T)}即可快速的构造一个Adapter
 * Created by RenT on 2016/4/23.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;
    protected SparseIntArray mViewTypes;
    protected ViewGroup mParent;

    /**
     * 假如使用该构造方法，必须使用{@link #addViewType(int, int)}添加布局
     */
    public CommonAdapter(Context context, List<T> data) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mViewTypes = new SparseIntArray();
    }

    /**
     * 单个布局
     */
    public CommonAdapter(Context context, List<T> data, int layoutId) {
        this(context, data);
        addViewType(0, layoutId);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
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
    public int getItemViewType(int position) {
        if (mViewTypes.size() > 1) {
            throw new IllegalStateException("You must override getItemViewType(int) when added more than 1 viewType");
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        if (mViewTypes.size() == 0) {
            throw new IllegalStateException("add 1 viewType at least");
        }
        return mViewTypes.size();
    }

    public void addViewType(int viewType, int layoutId) {
        mViewTypes.put(viewType, layoutId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mParent == null) {
            this.mParent = parent;
        }
        ViewHolder holder;
        if (convertView == null) {
            int layoutId = mViewTypes.get(getItemViewType(position));
            convertView = mInflater.inflate(layoutId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convert(position, holder, mData.get(position));
        return convertView;
    }

    protected abstract void convert(int position, ViewHolder holder, T model);

    public List<T> getData() {
        return mData;
    }

    public Context getContext() {
        return mContext;
    }

    public ViewGroup getParent() {
        return mParent;
    }
}