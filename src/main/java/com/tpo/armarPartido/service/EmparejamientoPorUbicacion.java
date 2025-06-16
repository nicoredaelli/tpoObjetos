package com.tpo.armarPartido.service;


import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;


@Component
public class EmparejamientoPorUbicacion implements EstrategiaEmparejamiento {

    private static final int DISTANCIA_MAXIMA = 10; 
    
    @Override
	public String toString() {
		return "Emparejamiento Por Ubicacion";
	}

	@Override
    public List<Usuario> emparejar(Partido partido, List<Usuario> jugadores) {
        Ubicacion ubicacionPartido = partido.getUbicacion();
        List<Usuario> jugadoresSeleccionados = new ArrayList<>();
        int jugadorCreador = 0;
        jugadoresSeleccionados.add(partido.getJugadoresParticipan().get(jugadorCreador));
        for (Usuario jugador : jugadores) {
            if (jugadoresSeleccionados.contains(jugador)) {
                continue; // Saltar si ya está seleccionado
            }
            double distancia = calcularDistancia(ubicacionPartido, jugador.getUbicacion());

            if (distancia <= DISTANCIA_MAXIMA) {
                jugadoresSeleccionados.add(jugador);
                System.out.println("Emparejando Jugadores por Ubicacion " + jugador.getNombre() + " cerca, agregado.");

                if (jugadoresSeleccionados.size() >= partido.getCantidadJugadores()) {
                    break;
                }
            }
            else{
                System.out.println("Emparejando Jugadores por Ubicacion " + jugador.getNombre() + " Muy Lejos");
            }
        }

        return jugadoresSeleccionados;
    }

    @Override
    public String getNombreEstrategia() {
        return "Por Ubicación Cercana";
    }

    private double calcularDistancia(Ubicacion ubi1, Ubicacion ubi2) {
        double x = ubi1.getLatitud() - ubi2.getLatitud();
        double y = ubi1.getLongitud() - ubi2.getLongitud();
        return Math.sqrt(x * x + y * y); 
    }
}

