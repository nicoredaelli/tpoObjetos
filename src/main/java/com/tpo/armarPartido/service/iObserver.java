package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;


public interface iObserver {
    void actualizar(Notificacion notificacion);
}