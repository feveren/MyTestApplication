package com.rent.mytestapplication.widget.linearlist;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/4.
 */
public abstract class LinearAdapter {

    protected abstract int getCount();

    protected abstract View getView(int position, ViewGroup parent);

}
