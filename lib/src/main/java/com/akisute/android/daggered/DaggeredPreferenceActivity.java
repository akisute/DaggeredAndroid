package com.akisute.android.daggered;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public abstract class DaggeredPreferenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
