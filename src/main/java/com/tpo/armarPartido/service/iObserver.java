package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;

/**
 * Observador de eventos de partido.
 */
public interface iObserver {
    void update(Notificacion notificacion);
}