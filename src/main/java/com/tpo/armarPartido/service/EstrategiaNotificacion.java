package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;

import java.util.List;

public interface EstrategiaNotificacion {
    void enviarNotificacion(Notificacion notificacion);
}