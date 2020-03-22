package zw.nash.recieptsapp.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zw.nash.recieptsapp.model.User;

public interface AuthApi {

    @GET("login")
    Flowable<User> getUser(
            @Query("username") String username,
            @Query("password") String password);

}
