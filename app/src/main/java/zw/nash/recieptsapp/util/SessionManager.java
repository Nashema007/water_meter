package zw.nash.recieptsapp.util;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import zw.nash.recieptsapp.model.User;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";
    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authWithId(final LiveData<AuthResource<User>> source){
        if(cachedUser !=null){
            cachedUser.setValue(AuthResource.loading(null));
            cachedUser.addSource(source, userAuthResource -> {
                cachedUser.setValue(userAuthResource);
                cachedUser.removeSource(source);
            });
        }
        else {
            Log.d(TAG, "authWithId: previous session detected. Retrieve user cache.");
        }
    }

    public void logout(){
        Log.d(TAG, "logout:  logging out...");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }
}
