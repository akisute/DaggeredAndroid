package com.akisute.android.daggered;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class DaggeredActionBarActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
