package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.service.EstrategiaNotificacion;
import com.tpo.armarPartido.service.EstrategiaNotificacionSMS;
import com.tpo.armarPartido.service.EstrategiaNotificacionMail;
import com.tpo.armarPartido.service.AdapterNotificacionMail;
import com.tpo.armarPartido.service.iObserver;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificador implements iObserver {
    private EstrategiaNotificacion estrategiaDeNotificacion;
    private AdapterNotificacionMail adapterNotificacionMail;

    @Override
    public void actualizar(Partido partido) {
        // Este método se ejecuta cuando el Partido notifica un cambio
        enviarNotificacionesAUsuarios(partido);
    }

    private void enviarNotificacionesAUsuarios(Partido partido) {
        // Todos usuarios del partido desde la notificación
        if (partido != null && partido.getJugadoresParticipan() != null) {
            for (Usuario usuario : partido.getJugadoresParticipan()) {
                // Cada usuario tiene su propia estrategia de notificación
                MedioNotificacion estrategiaUsuario = usuario.getMedioNotificacion();

                // Case switch en base a mi Estrategia de usuario
                if (estrategiaUsuario != null) {
                    switch (estrategiaUsuario) {
                        case SMS:
                            this.estrategiaDeNotificacion = new EstrategiaNotificacionSMS();
                            break;
                        case EMAIL:
                            this.estrategiaDeNotificacion = new EstrategiaNotificacionMail(adapterNotificacionMail);
                            break;
                        default:
                            System.out.println("Medio de notificación no soportado: " + estrategiaUsuario);
                            continue; // Salta al siguiente usuario
                    }

                    // Enviamos la notificacion en base a la estrategia seleccionada
                    estrategiaDeNotificacion.enviarNotificacion(
                            new Notificacion(partido.getEstado().getMessage(), usuario)
                    );
                }
            }
        }
    }

    public void cambiarEstrategiaNotificacion(EstrategiaNotificacion estrategiaNueva) {
        this.estrategiaDeNotificacion = estrategiaNueva;
    }
}