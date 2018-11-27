package com.example.digital.moma.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.digital.moma.R;
import com.example.digital.moma.controller.ObraController;
import com.example.digital.moma.model.Obra;
import com.example.digital.moma.utils.ResultListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Obra> listaObras = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ObrasAdapter obrasAdapter = new ObrasAdapter(listaObras);
        final ObraController obraController = new ObraController();
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
}
