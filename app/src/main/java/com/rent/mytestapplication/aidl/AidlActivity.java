package com.rent.mytestapplication.aidl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rent.mytestapplication.R;

public class AidlActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        textView = (TextView) findViewById(R.id.text);
        textView.setText(Process.myPid() + "\n" + this);
        System.out.println(textView.getText());
    }

    public void newProcess(View v) {
        textView.setText(Process.myPid() + "\n" + this);
        startActivityForResult(new Intent(this, ProcessActivity.class)
                .putExtra("text", "from AidlActivity"), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        textView.append("\n" + data.getStringExtra("result"));
    }
}
