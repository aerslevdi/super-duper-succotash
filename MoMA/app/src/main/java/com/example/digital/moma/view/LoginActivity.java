package com.example.digital.moma.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digital.moma.R;
import com.example.digital.moma.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private  CallbackManager callbackManager;

    private LoginButton loginButton;


    private TextView textViewNombreFacebook;
    private ImageView imageViewFacebook;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;



    @Override
    protected void onStart() {
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();

        ingreso(currentUser);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedin = accessToken != null && !accessToken.isExpired();
        if (isLoggedin){
            ingreso(currentUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        //callbackManager = CallbackManager.Factory.create();
        firebaseAuth = FirebaseAuth.getInstance();
        // BUCAR COMPONENTES
        imageViewFacebook = findViewById(R.id.imageViewFacebook);
        textViewNombreFacebook = findViewById(R.id.textViewNombreFacebook);


        //LOGIN CON FACEBOOK
        callbackManager = CallbackManager.Factory.create();


        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void ingreso(FirebaseUser user){
        if (user !=null){
            findViewById(R.id.login_button).setVisibility(View.GONE);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            User userInfo = new User(Profile.getCurrentProfile().getName(), Profile.getCurrentProfile().getProfilePictureUri(500,500));
            bundle.putString(MainActivity.KEY_NAME, userInfo.getName());

            bundle.putParcelable(MainActivity.KEY_IMAGE, userInfo.getImage());
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            findViewById(R.id.login_button).setVisibility(View.VISIBLE);
        }

    }
    private void handleFacebookAccessToken(AccessToken token) {


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            ingreso(user);
                        } else {
                            Toast.makeText(LoginActivity.this, "Fallo autenticacion",
                                    Toast.LENGTH_SHORT).show();
                            ingreso(null);
                        }

                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
