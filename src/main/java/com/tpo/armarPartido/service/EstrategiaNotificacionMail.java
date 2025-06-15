package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EstrategiaNotificacionMail implements EstrategiaNotificacion {
    private AdapterNotificacionMail adapterNotificacionMail;

    public EstrategiaNotificacionMail(AdapterNotificacionMail adapter) {
        this.adapterNotificacionMail = adapter;
    }

    @Override
    public void enviarNotificacion( Notificacion notificacion) {
        adapterNotificacionMail.notificar(notificacion);
    }
}

