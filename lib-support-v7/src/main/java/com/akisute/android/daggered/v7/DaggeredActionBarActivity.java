package com.akisute.android.daggered.v7;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.akisute.android.daggered.DaggeredApplication;

public abstract class DaggeredActionBarActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
