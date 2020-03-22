package zw.nash.recieptsapp.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zw.nash.recieptsapp.view.fragments.ProfileFragment;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

//    @ContributesAndroidInjector
//    abstract PostsFragment contributePostsFragment();
}
