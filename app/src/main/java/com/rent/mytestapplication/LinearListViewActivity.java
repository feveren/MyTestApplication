package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rent.mytestapplication.widget.linearlist.CheckedItemAdapter;
import com.rent.mytestapplication.widget.linearlist.LinearDividerLayout;
import com.rent.mytestapplication.widget.linearlist.MultiCheckedItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class LinearListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_list_view);

        LinearDividerLayout linearListView = (LinearDividerLayout) this.findViewById(R.id.layout_linear_list);
        List<CheckedItemAdapter.Item> list = new ArrayList<>();
        list.add(new CheckedItemAdapter.Item("key1", "单选1"));
        list.add(new CheckedItemAdapter.Item("key2", "单选2"));
        list.add(new CheckedItemAdapter.Item("key3", "单选3"));
        CheckedItemAdapter checkedItemAdapter = new CheckedItemAdapter(this, list);
        linearListView.setAdapter(checkedItemAdapter);

        LinearDividerLayout linearListView2 = (LinearDividerLayout) this.findViewById(R.id.layout_linear_list2);
        List<MultiCheckedItemAdapter.Item> list2 = new ArrayList<>();
        list2.add(new MultiCheckedItemAdapter.Item("key1", "多选1"));
        list2.add(new MultiCheckedItemAdapter.Item("key2", "多选2", true));
        list2.add(new MultiCheckedItemAdapter.Item("key3", "多选3"));
        MultiCheckedItemAdapter multiCheckedItemAdapter = new MultiCheckedItemAdapter(this, list2);
        linearListView2.setAdapter(multiCheckedItemAdapter);
    }
}
