package com.akisute.android.daggered;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public abstract class DaggeredFragmentActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
