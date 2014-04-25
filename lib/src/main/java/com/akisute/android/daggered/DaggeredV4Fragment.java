package com.akisute.android.daggered;

import android.app.Activity;

public abstract class DaggeredV4Fragment extends android.support.v4.app.Fragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DaggeredApplication application = (DaggeredApplication) activity.getApplication();
        application.inject(this);
    }
}
