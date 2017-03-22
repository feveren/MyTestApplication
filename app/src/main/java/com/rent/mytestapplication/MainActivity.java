package com.rent.mytestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rent.mytestapplication.activityoptions.ActOptsActivity;
import com.rent.mytestapplication.aidl.AidlActivity;
import com.rent.mytestapplication.anim.BallFallingActivity;
import com.rent.mytestapplication.anim.VectorActivity;
import com.rent.mytestapplication.mvp.MVPActivity;
import com.rent.mytestapplication.retrofit.RetrofitActivity;
import com.rent.mytestapplication.touchevent.NestedScrollActivity;
import com.rent.mytestapplication.touchevent.TouchEventActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> list = new ArrayList<>();
        list.add(removePkgName(NestedScrollViewActivity.class));
        list.add(removePkgName(CustomBehaviorActivity.class));
        list.add(removePkgName(AnimActivity.class));
        list.add(removePkgName(ButterKnifeActivity.class));
        list.add(removePkgName(ViewPagerActivity.class));
        list.add(removePkgName(StableIDsActivity.class));
        list.add(removePkgName(LinearListViewActivity.class));
        list.add(removePkgName(FragmentSwitcherActivity.class));
        list.add(removePkgName(PagerFragmentActivity.class));
        list.add(removePkgName(TabBarActivity.class));
        list.add(removePkgName(MVPActivity.class));
        list.add(removePkgName(TouchEventActivity.class));
        list.add(removePkgName(NestedScrollActivity.class));
        list.add(removePkgName(AidlActivity.class));
        list.add(removePkgName(RetrofitActivity.class));
        list.add(removePkgName(RxLifecycleActivity.class));
        list.add(removePkgName(BallFallingActivity.class));
        list.add(removePkgName(VectorActivity.class));
        list.add(removePkgName(ActOptsActivity.class));
        list.add(removePkgName(com.rent.mytestapplication.nestedscroll.NestedScrollActivity.class));
        list.add(removePkgName(TextImageSpanActivity.class));
        list.add("method:testClick");
        list.add("method:showBottomSheet");

        ListView listView = (ListView) this.findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String className = list.get(position);
                    if (className.startsWith("method")) {
                        MainActivity.this.getClass().getMethod(className.split(":")[1])
                                .invoke(MainActivity.this);
                    } else {
                        startActivity(new Intent(MainActivity.this, Class.forName(addPkgName(className))));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private String removePkgName(Class<?> clazz) {
        return clazz.getName().replace(BuildConfig.APPLICATION_ID + ".", "");
    }
    
    private String addPkgName(String className) {
        return BuildConfig.APPLICATION_ID + "." + className;
    }

    long[] mClicks = new long[5];

    public void testClick() {
        for (int i = 0; i < mClicks.length; i++) {
            System.out.print(mClicks[i] + " ");
        }
        System.out.println();
        System.arraycopy(mClicks, 1, mClicks, 0, mClicks.length - 1);
        mClicks[mClicks.length - 1] = SystemClock.uptimeMillis();
        if (mClicks[0] >= (SystemClock.uptimeMillis() - 500)) {
            System.out.println("clicked 5 times");
            Toast.makeText(this, "click 5 times", Toast.LENGTH_SHORT).show();
        }
    }

    public void showBottomSheet() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        textView.setGravity(Gravity.CENTER);
        textView.setText("this is BottomSheetDialog");
        dialog.setContentView(textView);
//        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
