package zw.nash.recieptsapp.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zw.nash.recieptsapp.view.fragments.DashboardFragment;
import zw.nash.recieptsapp.view.fragments.PaymentFragment;
import zw.nash.recieptsapp.view.fragments.ProfileFragment;
import zw.nash.recieptsapp.view.fragments.WaterBillFragment;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

 @ContributesAndroidInjector
    abstract DashboardFragment contributeDashboardFragment();

 @ContributesAndroidInjector
    abstract PaymentFragment contributePaymentFragment();

 @ContributesAndroidInjector
    abstract WaterBillFragment contributeWaterBillFragment();

//    @ContributesAndroidInjector
//    abstract PostsFragment contributePostsFragment();
}
