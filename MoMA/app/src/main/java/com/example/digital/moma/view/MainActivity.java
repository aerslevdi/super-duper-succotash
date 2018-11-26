package com.example.digital.moma.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.digital.moma.R;
import com.example.digital.moma.controller.ObraController;
import com.example.digital.moma.model.Obra;
import com.example.digital.moma.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Obra> listaObras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ObrasAdapter obrasAdapter = new ObrasAdapter(listaObras);
        final ObraController obraController = new ObraController();

        obraController.traerObras(this, new ResultListener<List<Obra>>() {
            @Override
            public void finish(List<Obra> resultado) {
                listaObras = resultado;
                obrasAdapter.setObraList(listaObras);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(obrasAdapter);
    }
}
