package com.tpo.armarPartido.notificacion;

import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encargado de enviar notificaciones a través de los distintos canales.
 */
public class Notificador {

    private final List<NotificacionSender> canales = new ArrayList<>();
    private final Map<MedioNotificacion, NotificacionSender> canalesPorMedio = new HashMap<>();

    /**
     * Registra un canal genérico. Mantiene compatibilidad con implementaciones
     * anteriores que usan una lista simple de canales.
     */
    public void agregarCanal(NotificacionSender canal) {
        if (canal != null) {
            canales.add(canal);
        }
    }

    /**
     * Asocia un canal específico a un medio de notificación.
     * Si ya había un canal para ese medio se reemplaza por el nuevo.
     */
    public void agregarCanal(MedioNotificacion medio, NotificacionSender canal) {
        if (medio != null && canal != null) {
            canalesPorMedio.put(medio, canal);
        }
    }

    public void enviarNotificacion(Usuario usuario, String mensaje) {
        Notificacion n = new Notificacion(mensaje);
        // Si se definió un canal específico para el medio del usuario, se
        // utiliza ese. En caso contrario se utilizan todos los canales registrados.
        NotificacionSender canalPorMedio = canalesPorMedio.get(usuario.getMedioNotificacion());
        if (canalPorMedio != null) {
            canalPorMedio.enviar(usuario, n);
        } else {
            for (NotificacionSender canal : canales) {
                canal.enviar(usuario, n);
            }
        }
    }
}