package com.tpo.armarPartido.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.EstrategiaEmparejamiento;
import com.tpo.armarPartido.service.iObserver;
import com.tpo.armarPartido.service.estados.EstadoPartido;

public class ControllerPartido {
	
    private static ControllerPartido instancia;
    private List<Partido> partidos;

    private ControllerPartido() {
    	partidos = new ArrayList<>();
    }

    public static ControllerPartido getInstancia() {
        System.out.println("Inicio Controlador de partidos ");
    	if (instancia == null) {
            instancia = new ControllerPartido();
        }
        return instancia;
    }
    
    public void crearPartido(Deporte deporte, int cantidadJugadores, int duracion, Ubicacion ubicacion, Date horario, EstadoPartido estado, EstrategiaEmparejamiento emparejamiento, List<Usuario> jugadoresParticipan, Nivel nivel, List<iObserver> observadores) {
    	Partido nuevo = new Partido(deporte, cantidadJugadores, duracion, ubicacion, horario, estado, emparejamiento, jugadoresParticipan, nivel, observadores);
    	partidos.add(nuevo);
    	System.out.println("Se creo un nuevo partido de " + nuevo.getDeporte());
    }

}
