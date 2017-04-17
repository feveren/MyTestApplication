package com.rent.mytestapplication.activityoptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.rent.mytestapplication.R;

public class ActOptsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_opts);
    }

    public void start(View v) {
        ViewCompat.setTransitionName(this.findViewById(R.id.search_bar), "searchBar");
        Intent intent = new Intent(this, ActOptsSecondActivity.class);
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "searchBar").toBundle());
    }
}
