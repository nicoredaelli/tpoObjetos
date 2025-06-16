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
	public String toString() {
		return "Emparejamiento Por Nivel";
	}

	@Override
    public List<Usuario> emparejar(Partido partido, List<Usuario> jugadores) {
        Nivel nivelRequerido = partido.getNivel();
        Deporte deporte = partido.getDeporte();

        List<Usuario> jugadoresSeleccionados = new ArrayList<>();

        for (Usuario jugador : jugadores) {
            Nivel nivelJugador = jugador.getNivelesPorDeporte().get(deporte);

            if (nivelJugador != null && nivelJugador == nivelRequerido) {
                jugadoresSeleccionados.add(jugador);
                System.out.println("Emparejando Jugadores por Nivel " + jugador.getNombre() + " agregado.");

                if (jugadoresSeleccionados.size() >= partido.getCantidadJugadores()) {
                    break;
                }
            }
            else{
                System.out.println("Emparejando Jugadores por Nivel " + jugador.getNombre() + " no tiene el nivel suficiente.");

            }
        }

        return jugadoresSeleccionados;
    }

    @Override
    public String getNombreEstrategia() {
        return "Por Nivel de Habilidad";
    }
}
