package com.akisute.android.daggered;

import android.app.Activity;
import android.app.DialogFragment;

public class DaggeredDialogFragment extends DialogFragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DaggeredApplication application = (DaggeredApplication) activity.getApplication();
        application.inject(this);
    }
}
