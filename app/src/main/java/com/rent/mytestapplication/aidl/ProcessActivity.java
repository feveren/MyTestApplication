package com.rent.mytestapplication.aidl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rent.mytestapplication.R;

public class ProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(Process.myPid() + "\n" + this + "\n");
        System.out.println(textView.getText());
        textView.append(getIntent().getStringExtra("text"));
    }

    public void setResult(View v) {
        Intent intent = new Intent();
        intent.putExtra("result", "from ProcessActivity");
        setResult(RESULT_OK, intent);
        finish();
        Process.killProcess(Process.myPid());
    }

    @Override
    public void onBackPressed() {
        setResult(null);
    }
}
