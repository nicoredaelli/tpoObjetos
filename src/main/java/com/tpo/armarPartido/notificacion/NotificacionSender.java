package com.tpo.armarPartido.notificacion;

import com.tpo.armarPartido.model.Usuario;

/**
 * Canal de envío de notificaciones.
 */
public interface NotificacionSender {
    void enviar(Usuario usuario, Notificacion notificacion);
}