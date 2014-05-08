package com.akisute.android.daggered.v4;

import com.akisute.android.daggered.DaggeredApplication;

public abstract class DaggeredV4Application extends DaggeredApplication {
    public void inject(android.support.v4.app.Fragment fragment) {
        inject(fragment, fragment.getActivity());
    }
}
