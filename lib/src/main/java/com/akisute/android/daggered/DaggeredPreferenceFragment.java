package com.akisute.android.daggered;

import android.app.Activity;
import android.preference.PreferenceFragment;

public abstract class DaggeredPreferenceFragment extends PreferenceFragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DaggeredApplication application = (DaggeredApplication) activity.getApplication();
        application.inject(this);
    }
}
