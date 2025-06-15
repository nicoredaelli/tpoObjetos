package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Partido;

/**
 * Observador de eventos de partido.
 */

public interface iObserver {
    void update(Notificacion notificacion);
}