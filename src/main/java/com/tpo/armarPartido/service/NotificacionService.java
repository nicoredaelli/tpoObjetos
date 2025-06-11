package com.tpo.armarPartido.service;

import com.tpo.armarPartido.enums.EventoPartido;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Usuario;

import java.util.List;

/**
 * Servicio que actúa como observador de los partidos y envía las notificaciones
 * correspondientes a los jugadores.
 */
public class NotificacionService implements iObserver{
    private final Notificador notificador;
    private final List<Usuario> usuariosRegistrados;

    /**
     * @param notificador        encargado de delegar las notificaciones
     * @param usuariosRegistrados lista completa de usuarios del sistema
     */
    public NotificacionService(Notificador notificador, List<Usuario> usuariosRegistrados) {
        this.notificador = notificador;
        this.usuariosRegistrados = usuariosRegistrados;
    }

    /**
     * Se invoca al crear un nuevo partido para avisar a los usuarios cuyo deporte favorito coincide.
     */
    public void notificarNuevoPartido(Partido partido) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getDeportesFavoritos().contains(partido.getDeporte())) {
                notificador.enviarNotificacion(usuario, "Nuevo partido de " + partido.getDeporte());
            }
        }
        partido.agregarObservador(this);
    }

    @Override
    public void update(EventoPartido evento, Partido partido){
        switch (evento) {
            case PARTIDO_ARMADO, PARTIDO_CONFIRMADO, PARTIDO_EN_JUEGO, PARTIDO_FINALIZADO, PARTIDO_CANCELADO -> {
                for (Usuario jugador : partido.getJugadoresParticipan()) {
                    String mensaje = switch (evento) {
                        case PARTIDO_ARMADO -> "El partido se arm\u00f3";
                        case PARTIDO_CONFIRMADO -> "El partido fue confirmado";
                        case PARTIDO_EN_JUEGO -> "El partido est\u00e1 en juego";
                        case PARTIDO_FINALIZADO -> "El partido finaliz\u00f3";
                        case PARTIDO_CANCELADO -> "El partido fue cancelado";
                        default -> "";
                    };
                    notificador.enviarNotificacion(jugador, mensaje);
                }
            }
            default -> {
            }
        }
    }
}
