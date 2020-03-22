package zw.nash.recieptsapp.di.auth;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zw.nash.recieptsapp.api.AuthApi;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}