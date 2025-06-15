package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;

public class EstrategiaNotificacionSMS implements EstrategiaNotificacion{
    @Override
    public void enviarNotificacion( Notificacion notificacion) {
        System.out.println("SMS");
    }
}
