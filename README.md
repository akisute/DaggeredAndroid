# DaggeredAndroid

An easy way to integrate Dagger DI with Android applications. DaggeredAndroid provides you some convinient base classes to integrate.

Currently DaggeredAndroid runs with Dagger version 1.2.2. DaggeredAndroid requires Android SDK target version 11 (4.0) or later.

## Install

Copy all .java files in `com.akisute.android.daggered` under `lib/src/main/java` into your project, then resolve dependencies by adding following libraries into your project as well:

* dagger 1.2.2 (`com.squareup.dagger:dagger:1.2.2`)
* dagger-compiler 1.2.2 (`com.squareup.dagger:dagger-compiler:1.2.2`)

You may also build the project using gradle or Android Studio. Execute the task `jar` and you'll find a jar built under `build/libs/` directory.

Installing this library via maven or gradle is currently not available.


### support-v4 and support-v7 version

From DaggeredAndroid 2.0.0, Support libraries such as support-v4 and support-v7 are no longer supported by DaggeredAndroid.

## Usage

As mentioned first, DaggeredAndroid is just a set of abstract base classes of Application, Activity, Fragment and Service. So you need to subclass them in order to integrate DaggeredAndroid.

Options without subclassing provided base classes will not be available due to several technical restrictions.

### Making modules with DaggeredAndroid

The primally reason you feel difficult to use Dagger is how to deal with Context object in Android. DaggeredAndroid provides a `DaggeredApplicationModule` that provides 2 different Context object, the Application Context and the Injecting Context. Application Context is just a singleton instance of your Application class, suited to populate global services like SharedPreference. Injecting Context is an instance of Activity or Service which is just being injected by Dagger. You can use this context to populate context-specific services like LayoutInfrator.

If you'd like to inject Application Context, specify `@ForApplication` annotation before Context. Use `@ForInjecting` annotation if you prefer using Injecting Context. Ommiting these annotation when you inject Context will results in a compile error.

Here's an example of Custom Module class that uses `DaggeredAndroidModule`.

```java
@Module(
        includes = {
                DaggeredApplicationModule.class
        },
        injects = {
                // Your Activity, Fragment and Service (dynamically injected on appropriate timings by Daggered classes )
                MyFragment.class,
                MyService.class,
                MyActivity.class

                // Model (statically injected recursively when injection is happening, uses constructor injections)
                // Usually you will not add other than Activity, Fragment or Service
        }
)
public class AppModule {

    @Provides
    @Singleton
    GlobalEventBus provideGlobalEventBus() {
        return new GlobalEventBus();
    }

    @Provides
    @Singleton
    GlobalPreference provideGlobalPreference(@ForApplication Context context) {
        // Uses application context by @ForApplication annotation here
        // In GlobalPreference, context will be used to get SharedPreference service
        return new GlobalPreference(context);
    }

    @Provides
    ConsoleListAdapter provideConsoleListAdapter(@ForInjecting Context context) {
        // Uses injecting context by @ForInjecting annotation here
        // In MyAdapter, context will be used to get LayoutInflator service
        return new MyAdapter(context);
    }
}
```

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

## Future Plans

* Enable install via Maven (or Gradle).

