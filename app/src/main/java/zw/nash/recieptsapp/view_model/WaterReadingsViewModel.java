package zw.nash.recieptsapp.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zw.nash.recieptsapp.api.WaterReadingsApi;
import zw.nash.recieptsapp.model.WaterReading;
import zw.nash.recieptsapp.util.Resource;
import zw.nash.recieptsapp.util.SessionManager;

public class WaterReadingsViewModel extends ViewModel {

    private static final String TAG = "WaterReadingsViewModel";
    private SessionManager sessionManager;
    private WaterReadingsApi waterReadingsApi;
    private MediatorLiveData<Resource<List<WaterReading>>> waterReadings;

    @Inject
    public WaterReadingsViewModel(SessionManager sessionManager, WaterReadingsApi waterReadingsApi) {
        this.sessionManager = sessionManager;
        this.waterReadingsApi = waterReadingsApi;
    }

    public LiveData<Resource<List<WaterReading>>> observeWaterReadings() {
        if (waterReadings == null) {
            waterReadings = new MediatorLiveData<>();
            waterReadings.setValue(Resource.loading(null));

            final LiveData<Resource<List<WaterReading>>> source =
                    LiveDataReactiveStreams.fromPublisher(
                            waterReadingsApi.getWaterReading(
                                    "111111")//sessionManager.getAuthUser().getValue().data.getUsername()
                                    .onErrorReturn(throwable -> {
                                        WaterReading waterReadingError = new WaterReading();
                                        waterReadingError.setId(-1);
                                        ArrayList<WaterReading> waterReadingsList = new ArrayList<>();
                                        waterReadingsList.add(waterReadingError);
                                        return waterReadingsList;
                                    }).map((Function<List<WaterReading>, Resource<List<WaterReading>>>) waterReadings -> {
                                if (waterReadings.size() > 0) {
                                    if (waterReadings.get(0).getId() == -1) {
                                        return Resource.error("Something went wrong..", null);
                                    }
                                }
                                return Resource.success(waterReadings);
                            }).subscribeOn(Schedulers.io()));

            waterReadings.addSource(source, waterReadingsResource -> {
                Log.d(TAG, "observeAccount: " + waterReadingsResource.data.toString());
                waterReadings.setValue(waterReadingsResource);
                waterReadings.removeSource(source);
            });


        }

        return waterReadings;
    }
}
