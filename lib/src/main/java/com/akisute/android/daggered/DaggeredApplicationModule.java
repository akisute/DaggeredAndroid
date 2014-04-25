package com.akisute.android.daggered;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(library = true)
public class DaggeredApplicationModule {

    private final DaggeredApplication mApplication;

    public DaggeredApplicationModule(DaggeredApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @ForInjecting
    Context provideInjectingContext() {
        if (mApplication.getInjectingContext() == null) {
            throw new IllegalStateException("Cannot provide injecting context. @ForInjecting Context can only be injected during injections to Daggered Contexts. No Lazy Injections available as well.");
        }
        return mApplication.getInjectingContext();
    }
}
