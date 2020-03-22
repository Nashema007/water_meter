package zw.nash.recieptsapp.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zw.nash.recieptsapp.api.AuthApi;
import zw.nash.recieptsapp.model.User;
import zw.nash.recieptsapp.util.AuthResource;
import zw.nash.recieptsapp.util.SessionManager;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {

        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: viewmodel is working....");

    }

    public void authWithId(String username, String password){
        Log.d(TAG, "authWithId: attempting to login");
        sessionManager.authWithId(queryUser(username, password));

    }

    private LiveData<AuthResource<User>> queryUser(String username, String password){
        return   LiveDataReactiveStreams.fromPublisher(

                authApi.getUser(username, password)
                        // instead of calling onError(error happens)
                        .onErrorReturn(throwable -> {
                            User errorUser = new User();
                            errorUser.setId(-1);
                            return errorUser;
                        })
                        //if !error returns user object else returns error object
                        .map((Function<User, AuthResource<User>>) user -> {
                            if(user.getId() == -1){
                                return AuthResource.error("could not Authenticate", null);
                            }
                            return AuthResource.authenticated(user);
                        })
                        .subscribeOn(Schedulers.io())
        );
    }
    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }
}

