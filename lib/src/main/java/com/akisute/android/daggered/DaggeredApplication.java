package com.akisute.android.daggered;

import android.content.Context;

import java.util.Arrays;

import dagger.ObjectGraph;

public abstract class DaggeredApplication extends android.app.Application {

    private ObjectGraph mObjectGraph;
    private Context mInjectingContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(concatenate(new Object[]{new DaggeredApplicationModule(this)}, getModules()));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mObjectGraph = null;
    }

    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public void inject(Object object, Context injectingContext) {
        mInjectingContext = injectingContext;
        mObjectGraph.inject(object);
        mInjectingContext = null;
    }

    public void inject(Context context) {
        inject(context, context);
    }

    public void inject(android.app.Fragment fragment) {
        inject(fragment, fragment.getActivity());
    }

    public Context getInjectingContext() {
        return mInjectingContext;
    }

    protected abstract Object[] getModules();

    private static <T> T[] concatenate(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
