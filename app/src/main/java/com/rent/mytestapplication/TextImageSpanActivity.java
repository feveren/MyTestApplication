package com.rent.mytestapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextImageSpanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String text = "temp";
        Bitmap bitmap = Bitmap.createBitmap(400, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0xff000000);

        Paint paint = new Paint();
        paint.setColor(0xffffffff);
        paint.setTextSize(40);
        paint.setAntiAlias(false);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, 200, 60, paint);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        String placeholder = "[image]";
        String str = "text to ImageSpan " + placeholder;
        ImageSpan span = new ImageSpan(this, bitmap);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(span, str.indexOf(placeholder), str.indexOf(placeholder) + placeholder.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(spannableString);
        setContentView(textView);
    }
}
