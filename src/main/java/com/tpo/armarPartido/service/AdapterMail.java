package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;

public class AdapterMail implements AdapterNotificacionMail {

    @Override
    public void notificar(Notificacion notificacion) {
        System.out.println("Adaptar mail :" + notificacion.getMensaje());
    }
}
