package com.rent.mytestapplication;

import android.app.Activity;
import android.widget.Button;

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
        Button button = (Button) activity.findViewById(R.id.button1);
        System.out.println(button.getText());
    }

}
