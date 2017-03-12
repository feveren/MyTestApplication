package com.rent.mytestapplication.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.rent.mytestapplication.R;

public class TestServiceActivity extends AppCompatActivity {
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);

        if (getIntent().getBooleanExtra("bool", false)) {
            getWindow().getDecorView().setBackgroundColor(0x000000);
        }
    }

    public void startActivity(View v) {
        startActivity(new Intent(this, TestServiceActivity.class).putExtra("bool", true));
    }

    public void start(View v) {
        startService(new Intent(this, TestService.class));
    }

    public void bind(View v) {
        bindService(new Intent(this, TestService.class), connection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("TestService", TestServiceActivity.this.getClass().getSimpleName() + " onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("TestService", TestServiceActivity.this.getClass().getSimpleName() + " onServiceDisconnected");
            }
        }, BIND_AUTO_CREATE);
    }

    public void stop(View v) {
        stopService(new Intent(this, TestService.class));
    }

    public void unbind(View v) {
        if (connection != null) {
            unbindService(connection);
            connection = null;
        }
    }
}
