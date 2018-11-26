package com.example.digital.moma.dao;

import android.util.Log;

import com.example.digital.moma.model.ContenedorObras;
import com.example.digital.moma.model.Obra;
import com.example.digital.moma.utils.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DAOObras extends DAOHelper{
    private ServiceObras serviceObras;
    public static final String BASE_URL = "https://api.myjson.com/";

    public DAOObras(String base_url) {
        super(base_url);
        serviceObras = retrofit.create(ServiceObras.class);
    }

    public void traerObras(final ResultListener<List<Obra>> listenerController){
        Call<ContenedorObras> contenedorObrasCall = serviceObras.traerObras();
        //retrofit2.Call<ContenedorObras>call = serviceObras.traerObras();
        contenedorObrasCall.enqueue(new Callback<ContenedorObras>() {
            @Override
            public void onResponse(retrofit2.Call<ContenedorObras> call, Response<ContenedorObras> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    ContenedorObras contenedorObras = response.body();
                    listenerController.finish(contenedorObras.getObra());
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ContenedorObras> call, Throwable t) {
                Log.e("Retrofit", "Error");
            }
        });
    }


}
