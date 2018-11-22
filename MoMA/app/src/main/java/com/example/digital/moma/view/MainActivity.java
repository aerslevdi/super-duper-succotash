package com.example.digital.moma.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.digital.moma.R;
import com.example.digital.moma.controller.ObraController;
import com.example.digital.moma.model.Obra;

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
    }
}
