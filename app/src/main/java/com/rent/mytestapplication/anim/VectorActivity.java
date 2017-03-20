package com.rent.mytestapplication.anim;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.rent.mytestapplication.R;

public class VectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
    }

    public void arrowDown1(View v) {
        this.findViewById(R.id.arrow_down1).animate().rotationXBy(180);
    }

    public void animatedVector(View v) {
//        AnimatedVectorDrawableCompat drawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.arrow_switch);
//        ImageView imageView = ((ImageView) findViewById(R.id.animated_vector));
//        imageView.setImageDrawable(drawableCompat);
//        ((AnimatedVectorDrawableCompat) (imageView.getDrawable())).start();

        Drawable drawable = ((ImageView) findViewById(R.id.animated_vector)).getDrawable();
        ((Animatable) drawable).start();
    }

    public void animatedChecked(View v) {
        Drawable drawable = ((ImageView) findViewById(R.id.animated_checked)).getDrawable();
        ((Animatable) drawable).start();
    }

    public void menuSwitch(View v) {
        Drawable drawable = ((ImageView) findViewById(R.id.menu_switch)).getDrawable();
        ((Animatable) drawable).start();
    }
}
