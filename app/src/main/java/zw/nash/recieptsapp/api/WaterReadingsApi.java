package zw.nash.recieptsapp.api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zw.nash.recieptsapp.model.WaterReading;

public interface WaterReadingsApi {

    @GET("water")
    Flowable<List<WaterReading>> getWaterReading(@Query("accountNumber") String accountNumber);

    @GET("water")
    Flowable<List<WaterReading>> getWaterReadings(@Query("id") String username);
}
