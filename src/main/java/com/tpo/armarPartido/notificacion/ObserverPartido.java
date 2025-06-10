package com.tpo.armarPartido.notificacion;

import com.tpo.armarPartido.enums.EventoPartido;
import com.tpo.armarPartido.model.Partido;

/**
 * Observador de eventos de partido.
 */
public interface ObserverPartido {
    void update(EventoPartido evento, Partido partido);
}