package zw.nash.recieptsapp.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import zw.nash.recieptsapp.model.User;

public interface AuthApi {

    @GET("login")
    Flowable<User> getUser(@Header("Authorization") String userCred);

}
