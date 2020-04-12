package zw.nash.recieptsapp.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zw.nash.recieptsapp.model.UserDetails;

public interface ProfileApi {

    @GET("profile")
    Flowable<UserDetails> getProfile(@Query("username") String username);
}
