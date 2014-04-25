package com.akisute.android.daggered;

import android.app.Activity;

public abstract class DaggeredFragment extends android.app.Fragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DaggeredApplication application = (DaggeredApplication) activity.getApplication();
        application.inject(this);
    }
}
