package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;
import com.tpo.armarPartido.model.Usuario;



/**
 * Implementación de envío de notificaciones por correo electrónico.
 * En este proyecto se simula con una impresión por consola.
 */

public class EmailNotificacionSender implements NotificacionSender {

    @Override
    public void enviar(Usuario usuario, Notificacion notificacion){
        System.out.println("[EMAIL] a" + usuario.getCorreo() + ": " + notificacion.getMensaje());
    }
}
