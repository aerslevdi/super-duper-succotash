package com.example.digital.moma.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.digital.moma.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;

public class LoginActivity extends AppCompatActivity {

    private  CallbackManager callbackManager;

    @Override
    protected void onStart() {
        super.onStart();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedin = accessToken != null && !accessToken.isExpired();
        if (isLoggedin){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
    }
}
