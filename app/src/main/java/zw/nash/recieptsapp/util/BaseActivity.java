package zw.nash.recieptsapp.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import zw.nash.recieptsapp.view.activity.AuthActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SessionManager sessionManager;

    private static final String TAG = "BaseActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     subscribeObservers();
    }
    public void subscribeObservers(){
        sessionManager.getAuthUser().observe(this, userAuthResource -> {
            if(userAuthResource != null){
                switch (userAuthResource.status){
                    case LOADING:

                        break;
                    case AUTHENTICATED:

                        Log.d(TAG, "onChanged: LOGIN SUCCESS "+ userAuthResource.data.getEmail());
                        break;
                    case ERROR:

                        Toast.makeText(BaseActivity.this,
                                userAuthResource.message+"\nDid you enter a number between 1 and 10",
                                Toast.LENGTH_LONG).show();
                        break;
                    case NOT_AUTHENTICATED:
                        onLoginScreen();
                        break;
                }

            }
        });
    }
    private void onLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }


}
