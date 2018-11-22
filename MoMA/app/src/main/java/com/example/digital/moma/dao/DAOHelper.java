package com.example.digital.moma.dao;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOHelper {
    protected Retrofit retrofit;

    public DAOHelper(String base_url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(base_url)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();
    }
}
