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

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import zw.nash.recieptsapp.R;
import zw.nash.recieptsapp.model.Account;
import zw.nash.recieptsapp.util.Resource;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;
import zw.nash.recieptsapp.view_model.WaterBillViewModel;

public class WaterBillFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private WaterBillViewModel waterBillViewModel;
    private static final String TAG = "WaterBillFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_bill, container, false);

        waterBillViewModel = new ViewModelProvider(getActivity(), viewModelProviderFactory).get(WaterBillViewModel.class);
        subscribeObserver();


        return view;
    }

    private void subscribeObserver(){
        waterBillViewModel.observeAccount().removeObservers(getViewLifecycleOwner());
        waterBillViewModel.observeAccount().observe(getViewLifecycleOwner(), new Observer<Resource<Account>>() {
            @Override
            public void onChanged(Resource<Account> accountResource) {
                if(accountResource != null){
                    switch (accountResource.status){
                        case LOADING:
                            Log.d(TAG, "subscribeObservers: loading...");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "subscribeObservers: success...");
                            setDisplay(accountResource.data);
                            break;
                        case ERROR:
                            Log.e(TAG, "subscribeObservers: error..." + accountResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setDisplay(Account data){

        Log.d(TAG, "setDisplay: " + data.toString());

    }
}
