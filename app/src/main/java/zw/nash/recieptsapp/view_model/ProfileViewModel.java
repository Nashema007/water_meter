package zw.nash.recieptsapp.view_model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import zw.nash.recieptsapp.util.SessionManager;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";
    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: is ready");
    }

   /* public LiveData<AuthResource<User>> getAuthUser(){
        return sessionManager.getAuthUser();
    }*/


}
