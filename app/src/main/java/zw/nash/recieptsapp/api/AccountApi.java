package zw.nash.recieptsapp.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zw.nash.recieptsapp.model.Account;

public interface AccountApi {

    @GET("account")
    Flowable<Account> getAccount(@Query("username") String username);
}
