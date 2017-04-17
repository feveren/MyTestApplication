package com.rent.mytestapplication.activityoptions;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;

import com.rent.mytestapplication.R;

public class ActOptsSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_opts_second);

        ViewCompat.setTransitionName(this.findViewById(R.id.search_bar), "searchBar");
    }
}
