package zw.nash.recieptsapp.util;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import zw.nash.recieptsapp.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build();
    }
}
