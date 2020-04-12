package zw.nash.recieptsapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import zw.nash.recieptsapp.R;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;

public class ProfileFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }
}
