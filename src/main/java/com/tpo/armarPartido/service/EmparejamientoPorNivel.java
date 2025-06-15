package com.tpo.armarPartido.service;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmparejamientoPorNivel implements EstrategiaEmparejamiento {

    @Override
    public List<Usuario> emparejar(Partido partido, List<Usuario> jugadores) {
        Nivel nivelRequerido = partido.getNivel();
        Deporte deporte = partido.getDeporte();

        List<Usuario> jugadoresSeleccionados = new ArrayList<>();

        for (Usuario jugador : jugadores) {
            Nivel nivelJugador = jugador.getNivelesPorDeporte().get(deporte);

            if (nivelJugador != null && nivelJugador == nivelRequerido) {
                jugadoresSeleccionados.add(jugador);

                if (jugadoresSeleccionados.size() >= partido.getCantidadJugadores()) {
                    break;
                }
            }
        }

        return jugadoresSeleccionados;
    }

    @Override
    public String getNombreEstrategia() {
        return "Por Nivel de Habilidad";
    }
}
