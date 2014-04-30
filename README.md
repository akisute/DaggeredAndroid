# DaggeredAndroid

An easy way to integrate Dagger DI with Android applications. DaggeredAndroid provides you some convinient base classes to integrate.


## Install

Copy all .java files in `com.akisute.android.daggered` into your project, then resolve dependencies by adding following libraries into your project as well:

* dagger 1.2.+ (`com.squareup.dagger:dagger:1.2.+`)
* dagger-compiler 1.2.+ (`com.squareup.dagger:dagger-compiler:1.2.+`)
* support-v4 latest (`com.android.support:support-v4`)
    * basically you can omit support-v4 if you'd like but it requires some source code modifications
* appcompat-v7 latest (`com.android.support:appcompat-v7`) - optional
    * only if you'd like to use `DaggeredActionBarActivity`

Install via maven or gradle is currently not available.


## Usage

As mentioned first, DaggeredAndroid is just a set of abstract base classes of Application, Activity, Fragment and Service. So you need to subclass them in order to integrate DaggeredAndroid.

Options without subclassing provided base classes are currently not available yet.

### Making modules with DaggeredAndroid

### Integrate DaggeredAndroid into your app

First of all, subclass `DaggeredApplication`. Override `getModules` method to add your own Dagger Modules.

```java
public class MyApplication extends DaggeredApplication {

    @Override
    protected Object[] getModules() {
        return new Object[]{
                new AppModule()
        };
    }
}
```

Then subclass any Activities, Fragments and Services you'd like to inject by Dagger.

```java
public class MyFragment extends DaggeredFragment {

    // Designate fields with @Inject annotation of Dagger to be injected.
    @Inject
    GlobalEventBus mGlobalEventBus;
    @Inject
    MyAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // DaggeredAndroid will automatically inject this fragment. These fields are injected at this time.
        mAdapter.registerDataSetObserver(mDataSetObserver);
        mAdapter.load();
        mGlobalEventBus.register(this);
    }

}
```

Activities, Fragments and Services are automatically injected on appropriate timings. As long as you keep following on lifecycle of standard Android app, all injections should work properly.

