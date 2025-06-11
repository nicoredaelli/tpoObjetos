package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;
import com.tpo.armarPartido.model.Usuario;

/**
 * Implementación simulada de envíos push mediante Firebase.
 */

public class FirebaseNotificacionSender implements NotificacionSender {

    @Override
    public void enviar(Usuario usuario, Notificacion notificacion){
        System.out.println("[PUSH] a " + usuario.getNombre() + ": " + notificacion.getMensaje());
    }
}
