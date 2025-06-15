package com.tpo.armarPartido.service;


import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.EstrategiaEmparejamiento;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmparejamientoPorNivel implements EstrategiaEmparejamiento {

    @Override
    public List<Usuario> emparejar (Partido partido, List<Usuario> jugadores) {
        Nivel nivelRequerido = partido.getNivel();

        return jugadores.stream()
                .filter(jugador -> {
                    //Nivel nivelJugador = jugador.getNivel();  // ESPERANDO CREACION JUGADOR
                    Nivel nivelJugador = Nivel.AVANZADO;
                    return nivelJugador != null && nivelJugador == nivelRequerido;
                })
                .limit(partido.getCantidadJugadores())
                .collect(Collectors.toList());
    }

    @Override
    public String getNombreEstrategia() {
        return "Por Nivel de Habilidad";
    }
}