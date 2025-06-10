package com.tpo.armarPartido.notificacion;

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
