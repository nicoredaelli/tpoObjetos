package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;
import com.tpo.armarPartido.model.Partido;


public interface iObserver {
    void actualizar(Partido partido);
}