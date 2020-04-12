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
import zw.nash.recieptsapp.model.UserDetails;
import zw.nash.recieptsapp.util.Resource;
import zw.nash.recieptsapp.view_model.ProfileViewModel;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;

public class ProfileFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private static final String TAG = "ProfileFragment";
    private ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileViewModel = new ViewModelProvider(getActivity(), viewModelProviderFactory).get(ProfileViewModel.class);
        subscribeObserver();
        return view;
    }

    private void subscribeObserver() {
        profileViewModel.observeProfile().removeObservers(getViewLifecycleOwner());
        profileViewModel.observeProfile().observe(getViewLifecycleOwner(), new Observer<Resource<UserDetails>>() {
            @Override
            public void onChanged(Resource<UserDetails> userDetailsResource) {
                if (userDetailsResource != null) {
                    switch (userDetailsResource.status) {
                        case LOADING:
                            Log.d(TAG, "subscribeObservers: loading...");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "subscribeObservers: success...");
                            setDisplay(userDetailsResource.data);
                            break;
                        case ERROR:
                            Log.e(TAG, "subscribeObservers: error..." + userDetailsResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setDisplay(UserDetails data) {

        Log.d(TAG, "setDisplay: " + data.toString());

    }
}
