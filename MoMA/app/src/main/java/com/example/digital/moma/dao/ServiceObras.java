package com.example.digital.moma.dao;

import com.example.digital.moma.model.ContenedorObras;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceObras {

    @GET("bins/x858r")
    Call<ContenedorObras> traerObras();
}
