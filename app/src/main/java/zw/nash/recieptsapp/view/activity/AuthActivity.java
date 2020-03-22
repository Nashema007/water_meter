package zw.nash.recieptsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import zw.nash.recieptsapp.R;
import zw.nash.recieptsapp.view_model.AuthViewModel;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    public static final String TAG = AuthActivity.class.getSimpleName();
    private AuthViewModel authViewModel;
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);


        authViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel.class);
        subscribeObservers();
    }
    private void subscribeObservers(){
        authViewModel.observeAuthState().observe(this, userAuthResource -> {
            if(userAuthResource != null){
                switch (userAuthResource.status){
                    case LOADING:
                        showProgressBar(true);
                        break;
                    case AUTHENTICATED:
                        showProgressBar(false);
                        Log.d(TAG, "onChanged: LOGIN SUCCESS "+ userAuthResource.data.getEmail());
                        onLoginSuccess();
                        break;
                    case ERROR:
                        showProgressBar(false);
                        Toast.makeText(AuthActivity.this,
                                userAuthResource.message+"\nDid you enter the correct username or password",
                                Toast.LENGTH_LONG).show();
                        break;
                    case NOT_AUTHENTICATED:
                        showProgressBar(false);
                        break;
                }

            }
        });
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private  void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                attemptLogin();
                break;

        }
    }

    private void attemptLogin() {
        if(TextUtils.isEmpty(username.getText().toString().trim()) && TextUtils.isEmpty(password.getText().toString().trim())){
            return;
        }
        authViewModel.authWithId(username.getText().toString().trim(), password.getText().toString().trim());
    }
}
