package com.rent.mytestapplication.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.rent.mytestapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BallFallingActivity extends AppCompatActivity {
    private View mBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_falling);

        mBall = findViewById(R.id.ball);
    }

    public void animatorSet(View view) {
        final int height = 1000;
        final int a = 15;
        int h = 0;
        float v = 0;
        float loss = 0.6f;

        List<Animator> list = new ArrayList<>();
        do {
            int duration;
            ObjectAnimator animator = new ObjectAnimator();
            animator.setTarget(mBall);
            animator.setPropertyName("translationY");
            if (v == 0) { // 下降
                animator.setFloatValues(h, height);
                animator.setInterpolator(new AccelerateInterpolator());

                duration = (int) Math.sqrt((height - h) * 2 / a);
                h = height;
                v = duration * a;
            }
            // 上升
            else {
                duration = (int) (v / a);
                h = height - (int) (v * duration - 0.5 * a * duration * duration);
                v = 0;

                animator.setFloatValues(height, h);
                animator.setInterpolator(new DecelerateInterpolator());
            }
            animator.setDuration(duration * 100);
            list.add(animator);
            System.out.println(h + " " + v + " " + duration + " " + (v * loss));
        } while (v == 0 || (v *= loss) > 10);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(list);
        animatorSet.start();
    }

    public void bounceInterpolator(View v) {
        Animator anim = ObjectAnimator.ofFloat(mBall, "translationY", 0, 1000);
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(2000);
        anim.start();
    }

//    public void customInterpolator(View v) {
//        ObjectAnimator anim = ObjectAnimator.ofFloat(mBall, "translationY", 0, 1000);
//        anim.setInterpolator(new TimeInterpolator() {
//
//            public float getInterpolation(float t) {
//                return t;
//            }
//        });
//        anim.setDuration(2000);
//        anim.start();
//    }
}
