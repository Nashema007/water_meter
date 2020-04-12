package zw.nash.recieptsapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import zw.nash.recieptsapp.R;
import zw.nash.recieptsapp.model.WaterReading;
import zw.nash.recieptsapp.view.adapter.WaterReadingAdapter;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;
import zw.nash.recieptsapp.view_model.WaterReadingsViewModel;

public class WaterReadingsFragment extends DaggerFragment {

    private static final String TAG = "WaterReadingsFragment";
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    private WaterReadingsViewModel waterReadingsViewModel;
    @Inject
    WaterReadingAdapter waterReadingAdapter;
    private RecyclerView readingsRecyclerView;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_readings, container, false);

        waterReadingsViewModel = new ViewModelProvider(getActivity(), viewModelProviderFactory).get(WaterReadingsViewModel.class);
        initRecyclerView();
        subscribeObserver();
        return view;
    }

    private void subscribeObserver() {
        waterReadingsViewModel.observeWaterReadings().removeObservers(getViewLifecycleOwner());
        waterReadingsViewModel.observeWaterReadings().observe(getViewLifecycleOwner(), waterReadingResource -> {
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
        });
    }

    private void setDisplay(List<WaterReading> data) {
        waterReadingAdapter.setWaterReadings(data);
        Log.d(TAG, "setDisplay: " + data.toString());

    }

    private void initRecyclerView() {
        readingsRecyclerView = getActivity().findViewById(R.id.waterReadingRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        readingsRecyclerView.setLayoutManager(layoutManager);
        readingsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        readingsRecyclerView.setAdapter(waterReadingAdapter);
    }
}
