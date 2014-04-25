package com.akisute.android.daggered;


import android.app.IntentService;

public abstract class DaggeredIntentService extends IntentService {

    public DaggeredIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggeredApplication application = (DaggeredApplication) getApplication();
        application.inject(this);
    }
}
