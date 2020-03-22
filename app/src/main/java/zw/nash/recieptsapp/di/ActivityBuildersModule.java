package zw.nash.recieptsapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zw.nash.recieptsapp.di.auth.AuthModule;
import zw.nash.recieptsapp.di.auth.AuthScope;
import zw.nash.recieptsapp.di.auth.AuthViewModelsModule;
import zw.nash.recieptsapp.di.main.MainFragmentBuildersModule;
import zw.nash.recieptsapp.di.main.MainModule;
import zw.nash.recieptsapp.di.main.MainScope;
import zw.nash.recieptsapp.di.main.MainViewModelsModule;
import zw.nash.recieptsapp.view.activity.AuthActivity;
import zw.nash.recieptsapp.view.activity.MainActivity;

@Module
public abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class,
            AuthModule.class,})
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuildersModule.class,
            MainViewModelsModule.class,
            MainModule.class})
    abstract MainActivity contributeMainActivity();
}
