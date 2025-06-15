package com.tpo.armarPartido;


import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.*;
import com.tpo.armarPartido.service.estados.NecesitamosJugadores;
import com.tpo.armarPartido.service.estados.PartidoArmado;

import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Usuario Implementa el iObserver para poder notificar a cada usuario del partido.
        // Los observadores son los USUARIOS.
        // Crear usuarios con diferentes medios de notificación
        Usuario usuario1 = new Usuario("Ezequiel", "ezequiel@email.com", "pass123",
                List.of(Deporte.FUTBOL), List.of(Nivel.INTERMEDIO),
                MedioNotificacion.EMAIL, null);

        Usuario usuario2 = new Usuario("Dario", "dario@email.com", "pass456",
                List.of(Deporte.FUTBOL), List.of(Nivel.INTERMEDIO),
                MedioNotificacion.SMS, null);

        // Crear partido y agregar observadores
        Partido partido = new Partido(Nivel.INTERMEDIO,List.of(usuario1),new EmparejamientoPorNivel(),new NecesitamosJugadores(),new Date(5),new Ubicacion(),5,10,Deporte.FUTBOL);
        partido.agregarObservador(usuario1);
        partido.agregarObservador(usuario2);

        // Como cambio de estrategia de notificacion es lo que me queda la duda ?
        //partido.cambiarEstrategiaNotificacion(new NotificacionSMS());
        partido.cambiarEstado(new PartidoArmado());

    }
}