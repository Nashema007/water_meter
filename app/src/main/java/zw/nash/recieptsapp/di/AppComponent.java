package zw.nash.recieptsapp.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import zw.nash.recieptsapp.util.BaseApplication;
import zw.nash.recieptsapp.util.SessionManager;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}

