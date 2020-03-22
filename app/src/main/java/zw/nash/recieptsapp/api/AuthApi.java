package zw.nash.recieptsapp.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zw.nash.recieptsapp.model.User;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUser(@Path("id") int id);

}
