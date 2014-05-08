package com.akisute.android.daggered.v4;

import android.app.Activity;

import com.akisute.android.daggered.DaggeredApplication;

public abstract class DaggeredV4Fragment extends android.support.v4.app.Fragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DaggeredApplication application = (DaggeredApplication) activity.getApplication();
        application.inject(this);
    }
}
