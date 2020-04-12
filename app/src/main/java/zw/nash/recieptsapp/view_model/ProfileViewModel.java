package zw.nash.recieptsapp.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zw.nash.recieptsapp.api.ProfileApi;
import zw.nash.recieptsapp.model.UserDetails;
import zw.nash.recieptsapp.util.Resource;
import zw.nash.recieptsapp.util.SessionManager;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";
    private final SessionManager sessionManager;
    private ProfileApi profileApi;
    private MediatorLiveData<Resource<UserDetails>> profile;
    @Inject
    public ProfileViewModel(SessionManager sessionManager, ProfileApi profileApi) {
        this.sessionManager = sessionManager;
        this.profileApi = profileApi;
        Log.d(TAG, "ProfileViewModel: is ready");
    }

    public LiveData<Resource<UserDetails>> observeProfile() {
        if (profile == null) {
            profile = new MediatorLiveData<>();
            profile.setValue(Resource.loading(null));

            final LiveData<Resource<UserDetails>> source =
                    LiveDataReactiveStreams.fromPublisher(
                            profileApi.getProfile(
                                    "nashema")//sessionManager.getAuthUser().getValue().data.getUsername()
                                    .onErrorReturn(throwable -> {
                                        UserDetails userDetailsError = new UserDetails();
                                        userDetailsError.setName("");
                                        return userDetailsError;
                                    }).map((Function<UserDetails, Resource<UserDetails>>) userDetails -> {
                                if (userDetails == null) {
                                    return Resource.error("Something went wrong..", null);
                                }
                                return Resource.success(userDetails);
                            }).subscribeOn(Schedulers.io()));

            profile.addSource(source, new Observer<Resource<UserDetails>>() {
                @Override
                public void onChanged(Resource<UserDetails> userDetailsResource) {
                    Log.d(TAG, "observeProfile: " + userDetailsResource.data.toString());
                    profile.setValue(userDetailsResource);
                    profile.removeSource(source);
                }
            });


        }

        return profile;
    }

}
