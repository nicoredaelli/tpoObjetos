package com.tpo.armarPartido.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Notificador;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.AdapterMail;
import com.tpo.armarPartido.service.AdapterNotificacionMail;
import com.tpo.armarPartido.service.EstrategiaEmparejamiento;
import com.tpo.armarPartido.service.iObserver;
import com.tpo.armarPartido.service.estados.EstadoPartido;
import com.tpo.armarPartido.service.estados.NecesitamosJugadores;
import com.tpo.armarPartido.service.estados.PartidoArmado;

public class ControllerPartido {
	
    private static ControllerPartido instancia;
    private List<Partido> partidos;

    private ControllerPartido() {
    	partidos = new ArrayList<>();
    }

    public static ControllerPartido getInstancia() {
        System.out.println("Inicio Controlador de Partidos ");
    	if (instancia == null) {
            instancia = new ControllerPartido();
        }
        return instancia;
    }
    
    public void crearPartido(Deporte deporte, int cantidadJugadores, int duracion, Ubicacion ubicacion, Date horario, EstrategiaEmparejamiento emparejamiento, Usuario usuarioCreador, Nivel nivel) {
    	// Lo que necesito para crear cualquier Partido
    	EstadoPartido estadoInicial = new NecesitamosJugadores();
    	List<Usuario> listaJugadoresParticipan = new ArrayList<Usuario>();
    	listaJugadoresParticipan.add(usuarioCreador);
        AdapterNotificacionMail adapter = new AdapterMail(); // agrego el adaptermail
        Notificador notificador = new Notificador(adapter);
    	List<iObserver> observadores = new ArrayList<iObserver>();
    	observadores.add(notificador);
    	// Creo Partido con lista de usuarios con el primer usuario como creador (owner) y la lista de observadores con un observador notificador. 
    	Partido nuevo = new Partido(deporte, cantidadJugadores, duracion, ubicacion, horario, estadoInicial, emparejamiento, listaJugadoresParticipan, nivel, observadores);
    	partidos.add(nuevo);
    	System.out.println(" + Se creo un nuevo partido de " + nuevo.getDeporte());
    }
    
    public void buscarPartidosPorNivel(Nivel nivel) {
        System.out.println("Buscando partidos con nivel: " + nivel);
        boolean encontrados = false;

        for (int i = 0; i < partidos.size(); i++) {
            Partido partido = partidos.get(i);
            if (partido.getNivel().equals(nivel)) {
                System.out.println("ID Partido: " + i);
                System.out.println(" - Deporte: " + partido.getDeporte());
                System.out.println(" - Nivel: " + partido.getNivel());
                System.out.println(" - Ubicación: " + partido.getUbicacion());
                System.out.println(" - Horario: " + partido.getHorario());
                System.out.println(" - Cantidad de jugadores: " + partido.getCantidadJugadores());
                System.out.println(" - Jugadores actuales: " + partido.getJugadoresParticipan().size());
                System.out.println(" - Estado: " + partido.getEstado());
                System.out.println("-----------------------------------");
                encontrados = true;
            }
        }

        if (!encontrados) {
            System.out.println("No se encontraron partidos con el nivel: " + nivel);
        }
    }
    
    public Partido getPartidoPorID(int id) {
    	return partidos.get(id);

    }
    
    public void agregarJugadorAPartido(int idPartido, Usuario jugadorNuevo) {
    	Partido partido = getPartidoPorID(idPartido);
    	partido.agregarJugador(jugadorNuevo);
    	System.out.println("Se agrego jugador "+jugadorNuevo+" al partido: "+partido.getDeporte()+" del nivel: "+partido.getNivel());
    	System.out.println("-----------------------------------");
    	
    }
    
    public void printEstadoPartidoID (int id) {
    	System.out.println(partidos.get(id).getEstado()); 
    }
    
    public Partido getPartidoID(int id) {
    	return partidos.get(id);
    }
    
    public void armarPartido(int id) {
        Partido partido = partidos.get(id);

        if (partido.getEmparejamiento() != null) {
            System.out.println("🔄 Ejecutando Estrategia de emparejamiento...");

        // Simular todos los usuarios del sistema como candidatos
        List<Usuario> posiblesJugadores = ControllerUsuario.getInstancia().getUsuarios();

        // Aplicar la estrategia de emparejamiento del partido
        List<Usuario> seleccionados = partido.getEmparejamiento().emparejar(partido, posiblesJugadores);

        // Actualizar la lista de jugadores
        partido.setJugadoresParticipan(seleccionados);

        // Luego cambio de estado (por ejemplo a PartidoArmado si ya hay suficientes jugadores)
        if (seleccionados.size() >= partido.getCantidadJugadores()) {
            partido.setEstado(new PartidoArmado());
            System.out.println("✅ Partido armado con éxito con estrategia: " + partido.getEmparejamiento().getNombreEstrategia() + "\n");
        } else {
            System.out.println("⚠️ No se pudo armar el partido. Jugadores seleccionados: " + seleccionados.size());
        }

    }
        else{
            System.out.println("\n⚠️ No se selecciono ningun autocompletado");
            EstadoPartido estadoActual = partido.getEstado();
            estadoActual.armar(partido);
        }
    }
    
    public void confirmarPartido(int id, Usuario jugador) {
    	Partido partido = partidos.get(id);
    	EstadoPartido estadoActual = partido.getEstado();
    	if(partido.esParticipante(jugador)) {
	    	estadoActual.confirmar(partido);
	    	estadoActual.getMessage();
    	}
    	else {
    		System.err.println("El jugador que intenta confirmar no es parte del Partido");
		}
    	
    }
    

}
