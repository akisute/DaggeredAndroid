package com.akisute.android.daggered;


import android.app.Activity;
import android.os.Bundle;

public abstract class DaggeredActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
