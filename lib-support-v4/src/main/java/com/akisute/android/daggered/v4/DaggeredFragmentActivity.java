package com.akisute.android.daggered.v4;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.akisute.android.daggered.DaggeredApplication;

public abstract class DaggeredFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
