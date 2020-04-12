package zw.nash.recieptsapp.di.main;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zw.nash.recieptsapp.api.AccountApi;
import zw.nash.recieptsapp.api.ProfileApi;
import zw.nash.recieptsapp.api.WaterReadingsApi;

@Module
public class MainModule {

    @MainScope
    @Provides
    static AccountApi provideAccountApi(Retrofit retrofit) {
        return retrofit.create(AccountApi.class);
    }

    @MainScope
    @Provides
    static ProfileApi provideProfileApi(Retrofit retrofit) {
        return retrofit.create(ProfileApi.class);
    }

    @MainScope
    @Provides
    static WaterReadingsApi provideWaterReadingsApi(Retrofit retrofit) {
        return retrofit.create(WaterReadingsApi.class);
    }


//
//    @MainScope
//    @Provides
//    static PostAdapter providesAdapter(){
//        return new PostAdapter();
//    }
}
