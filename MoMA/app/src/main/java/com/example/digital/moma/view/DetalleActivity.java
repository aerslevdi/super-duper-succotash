package com.example.digital.moma.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.digital.moma.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetalleActivity extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        firebaseStorage = FirebaseStorage.getInstance();
        StorageReference raiz = firebaseStorage.getReference();
        StorageReference imagenes = raiz.child("artistpaints");

        drawerLayout = findViewById(R.id.mainLayout);
        navigationView = findViewById(R.id.drawer);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.obras:
                        //Ir a MainActivity
                        return true;
                    case R.id.chat:
                        //Ir a chat
                        return true;
                    case R.id.logout:
                        //logout
                        return true;
                }
                return false;
            }
        });


    }
}
