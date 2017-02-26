package com.rent.mytestapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rent.mytestapplication.common.LazyFragment;

/**
 * Created by Administrator on 2016/8/22.
 */
public class TestPagerFragment extends LazyFragment {
    private String mText;

    public TestPagerFragment(String text) {
        this.mText = text;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(mText + " onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(mText + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println(mText + " onCreateView");

        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(mText);
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println(mText + " onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(mText + " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println(mText + " onPause");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println(mText + " setUserVisibleHint isVisibleToUser is " + isVisibleToUser);
    }

    @Override
    public void requestData() {
        System.out.println(mText + " requestData");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println(mText + " onHiddenChanged hidden is " + hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(mText + " onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println(mText + " onDetach");
    }
}
