package com.rent.mytestapplication;

import android.app.Activity;
import android.widget.ListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by RenTao on 17/1/8.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestMainActivity {

    @Test
    public void testMain() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        ListView listView = (ListView) activity.findViewById(R.id.list_view);
        System.out.println(listView.getId());
    }

}
