package com.rent.mytestapplication.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.rent.mytestapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrool_view);

        ListView listView = (ListView) this.findViewById(R.id.list_view);
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String, String> map;
        for (int i = 0; i < 30; i++) {
            map = new HashMap<>();
            map.put("key", "item" + i);
            mapList.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_1,
                new String[] { "key" }, new int[] { android.R.id.text1 }));
    }
}
