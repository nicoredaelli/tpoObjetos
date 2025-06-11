package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;
import com.tpo.armarPartido.model.Usuario;

/**
 * Canal de envío de notificaciones.
 */
public interface NotificacionSender {
    void enviar(Usuario usuario, Notificacion notificacion);
}