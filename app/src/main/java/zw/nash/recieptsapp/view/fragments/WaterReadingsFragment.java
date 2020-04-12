package zw.nash.recieptsapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import zw.nash.recieptsapp.R;
import zw.nash.recieptsapp.model.WaterReading;
import zw.nash.recieptsapp.util.Resource;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;
import zw.nash.recieptsapp.view_model.WaterReadingsViewModel;

public class WaterReadingsFragment extends DaggerFragment {

    private static final String TAG = "WaterReadingsFragment";
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    private WaterReadingsViewModel waterReadingsViewModel;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_readings, container, false);

        waterReadingsViewModel = new ViewModelProvider(getActivity(), viewModelProviderFactory).get(WaterReadingsViewModel.class);
        subscribeObserver();
        return view;
    }

    private void subscribeObserver() {
        waterReadingsViewModel.observeWaterReadings().removeObservers(getViewLifecycleOwner());
        waterReadingsViewModel.observeWaterReadings().observe(getViewLifecycleOwner(), new Observer<Resource<List<WaterReading>>>() {
            @Override
            public void onChanged(Resource<List<WaterReading>> waterReadingResource) {
                if (waterReadingResource != null) {
                    switch (waterReadingResource.status) {
                        case LOADING:
                            Log.d(TAG, "subscribeObservers: loading...");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "subscribeObservers: success...");
                            setDisplay(waterReadingResource.data);
                            break;
                        case ERROR:
                            Log.e(TAG, "subscribeObservers: error..." + waterReadingResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setDisplay(List<WaterReading> data) {

        Log.d(TAG, "setDisplay: " + data.toString());

    }
}
