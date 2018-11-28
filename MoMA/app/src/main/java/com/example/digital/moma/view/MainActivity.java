package com.example.digital.moma.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.moma.R;
import com.example.digital.moma.controller.ObraController;
import com.example.digital.moma.model.Obra;
import com.example.digital.moma.utils.ResultListener;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ObrasAdapter.AdapterListener {
    private List<Obra> listaObras = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView userName;
    private ImageView userIcon;
    private View navHeader;
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    private String nombre;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ObrasAdapter obrasAdapter = new ObrasAdapter(listaObras, this);
        final ObraController obraController = new ObraController();
        drawerLayout = findViewById(R.id.mainLayout);
        navigationView = findViewById(R.id.drawer);
        navHeader = navigationView.getHeaderView(0);
        userIcon = navHeader.findViewById(R.id.userIcon);
        userName = navHeader.findViewById(R.id.userName);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = bundle.getString(KEY_NAME);
        userName.setText(nombre);
        uri =  getIntent().getParcelableExtra(KEY_IMAGE);
        Glide.with(this).load(uri).into(userIcon);


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
                        FirebaseAuth.getInstance().signOut();
                        return true;
                }
                return false;
            }
        });


        obraController.traerObras(this, new ResultListener<List<Obra>>() {
            @Override
            public void finish(List<Obra> resultado) {
                listaObras = resultado;
                obrasAdapter.setObraList(listaObras);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(obrasAdapter);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void irDetalle(Obra obra, Profile profile) {
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DetalleActivity.KEY_USER, nombre);
        bundle.putParcelable(DetalleActivity.KEY_IMG, uri);
        bundle.putString(DetalleActivity.KEY_ARTISTA, obra.getArtistId());
        bundle.putString(DetalleActivity.KEY_OBRA, obra.getImage());
        bundle.putString(DetalleActivity.KEY_TITULO, obra.getName());
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
