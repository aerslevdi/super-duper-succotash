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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digital.moma.R;
import com.example.digital.moma.model.Artista;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetalleActivity extends AppCompatActivity {

    private TextView userName;
    private ImageView userIcon;
    private View navHeader;
    private FirebaseStorage firebaseStorage;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView nombreObra;
    private TextView nombreArtista;
    private TextView influence;
    private TextView country;
    private ImageView imagenObra;
    private FirebaseDatabase mDatabase;
    public static final String KEY_OBRA = "obra";
    public static final String KEY_IMG = "img";
    public static final String KEY_AUTHOR = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //DRAWER -INICIO
        drawerLayout = findViewById(R.id.mLayout);
        navigationView = findViewById(R.id.navView);
        navHeader = navigationView.getHeaderView(0);
        userIcon = navHeader.findViewById(R.id.userIcon);
        userName = navHeader.findViewById(R.id.userName);
        /*Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String nombre = bundle.getString(KEY_USER);
        userName.setText(nombre);
        Uri uri =  getIntent().getParcelableExtra(KEY_IMG);
        Glide.with(this).load(uri).into(userIcon);*/


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.obras:
                        Intent intent = new Intent(DetalleActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.chat:
                        //Ir a chat
                        return true;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent login = new Intent(DetalleActivity.this, LoginActivity.class);
                        startActivity(login);
                        return true;
                }
                return false;
            }
        });
        //DRAWER - FINAL

        //SETEO FIREBASE

        firebaseStorage = FirebaseStorage.getInstance();
        StorageReference raiz = firebaseStorage.getReference();

        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();

        String nombre = bundle.getString(KEY_OBRA);
        String imagen = bundle.getString(KEY_IMG);
        final String autorID = bundle.getString(KEY_AUTHOR);
        Integer id = (Integer.parseInt(autorID)) - 1;

        mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference root = mDatabase.getReference();
        final DatabaseReference artists = root.child("artists");
        DatabaseReference child = artists.child(id.toString());
        //DatabaseReference idReference = mDatabase.getReference("artistID");

        //BUSCAR COMPONENTES

        imagenObra = findViewById(R.id.imagenObra);
        nombreArtista = findViewById(R.id.nameArtista);
        nombreObra = findViewById(R.id.nameObra);
        influence = findViewById(R.id.influence);
        country = findViewById(R.id.country);
        drawerLayout = findViewById(R.id.mLayout);
        navigationView = findViewById(R.id.navView);

        //SETEAR COMPONENTES

        nombreObra.setText(nombre);

        //SETEAR IMAGEN
        raiz.child(imagen).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(DetalleActivity.this).load(uri).into(imagenObra);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(DetalleActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        //SETEAR DATOS ARTISTA

        child.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Artista artist1 = dataSnapshot.getValue(Artista.class);
                String influencia = artist1.getInfluenced_by();
                String pais = artist1.getNationality();
                String nombre = artist1.getName();
                nombreArtista.setText(nombre);
                country.setText(pais);
                influence.setText(influencia);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
