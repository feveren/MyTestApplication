package com.rent.mytestapplication.anim;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.rent.mytestapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ReboundActivity extends AppCompatActivity {
    private final static String TAG_SPRING = ReboundActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebound);

        Rebound.add(this.findViewById(R.id.image_view), TAG_SPRING);
        Rebound.add(this.findViewById(R.id.image_view2), TAG_SPRING);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Rebound.removeByTag(TAG_SPRING);
    }

    public static class Rebound {
        private final static SpringSystem SPRING_SYSTEM = SpringSystem.create();
        private final static ArrayMap<String, List<String>> SPRINGS = new ArrayMap<>();

        public static void add(View v, String tag) {
            Spring spring = SPRING_SYSTEM.createSpring();
            spring.addListener(new ReboundListener(v));
            v.setOnTouchListener(new ReboundTouchListener(spring));

            addSprings(tag, spring.getId());
        }

        private static void addSprings(String tag, String id) {
            List<String> list = SPRINGS.get(tag);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(id);
            SPRINGS.put(tag, list);
        }

        public static void removeByTag(String tag) {
            List<String> list = SPRINGS.get(tag);
            if (list == null || list.isEmpty()) {
                return;
            }
            for (String id : list) {
                Spring spring = SPRING_SYSTEM.getSpringById(id);
                if (spring != null) {
                    try {
                        spring.destroy();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            SPRINGS.remove(tag);
        }

        public static class ReboundListener extends SimpleSpringListener {
            private View mView;

            public ReboundListener(View view) {
                this.mView = view;
            }

            @Override
            public void onSpringUpdate(Spring spring) {
                float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
                mView.setScaleX(mappedValue);
                mView.setScaleY(mappedValue);
            }
        }

        public static class ReboundTouchListener implements View.OnTouchListener {
            private Spring mSpring;

            public ReboundTouchListener(Spring spring) {
                this.mSpring = spring;
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mSpring.setEndValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mSpring.setEndValue(0);
                        break;
                }
                return true;
            }
        }
    }

}
