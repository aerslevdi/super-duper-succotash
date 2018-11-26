package com.example.digital.moma.controller;

import android.content.Context;

import com.bumptech.glide.util.Util;
import com.example.digital.moma.dao.DAOHelper;
import com.example.digital.moma.dao.DAOObras;
import com.example.digital.moma.model.ContenedorObras;
import com.example.digital.moma.model.Obra;
import com.example.digital.moma.utils.ResultListener;

import java.util.List;

public class ObraController {
    public void traerObras(Context context, final ResultListener<List<Obra>> listenerView) {
        if (com.example.digital.moma.utils.Util.isOnline(context)) {
            DAOObras daoObras = new DAOObras(DAOObras.BASE_URL);
            daoObras.traerObras(new ResultListener<List<Obra>>() {
                @Override
                public void finish(List<Obra> resultado) {
                    listenerView.finish(resultado);
                }
            });
        } else {
            // BASE DE DATOS
        }
    }
}
