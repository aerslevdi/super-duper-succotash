package com.example.digital.moma.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.moma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetalleActivity extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView userName;
    private ImageView userIcon;
    private View navHeader;
    public static final String KEY_USER = "user";
    public static final String KEY_IMG = "img";
    public static final String KEY_OBRA = "obra";
    public static final String KEY_TITULO ="titulo";
    public static final String KEY_ARTISTA = "artista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //DRAWER -INICIO
        drawerLayout = findViewById(R.id.mainLayout);
        navigationView = findViewById(R.id.drawer);
        drawerLayout = findViewById(R.id.mainLayout);
        navigationView = findViewById(R.id.drawer);
        navHeader = navigationView.getHeaderView(0);
        userIcon = navHeader.findViewById(R.id.userIcon);
        userName = navHeader.findViewById(R.id.userName);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String nombre = bundle.getString(KEY_USER);
        userName.setText(nombre);
        Uri uri =  getIntent().getParcelableExtra(KEY_IMG);
        Glide.with(this).load(uri).into(userIcon);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.obras:
                        Intent intent = new Intent(DetalleActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.chat:
                        //Ir a chat
                        return true;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        return true;
                }
                return false;
            }
        });

        //DRAWER - FINAL



    }
}
