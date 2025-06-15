package com.tpo.armarPartido;

import java.util.ArrayList;
import java.util.List;

import com.tpo.armarPartido.controller.ControllerPartido;
import com.tpo.armarPartido.controller.ControllerUsuario;
import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Ubicacion;

public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("Inicio ArmarPartido");
    
    	// Creo controlador y usuarios. 
    	ControllerUsuario userController = ControllerUsuario.getInstancia();
    	ControllerPartido partidoController = ControllerPartido.getInstancia();
    	
    	List<Deporte> listaDeportesUsar = new ArrayList();
    	listaDeportesUsar.add(Deporte.BASQUET);
    	List<Nivel> listaNivelUsar = new ArrayList<>();
    	listaNivelUsar.add(Nivel.AVANZADO);
    	
    	userController.crearUsuario("Juan Perez", "juan@example.com", "1234", listaDeportesUsar, listaNivelUsar
    			, MedioNotificacion.EMAIL, new Ubicacion(1, 1));
    	
        userController.crearUsuario("Ana Gómez", "ana@example.com", "abcd", listaDeportesUsar, listaNivelUsar,
                MedioNotificacion.SMS, new Ubicacion(2, 3));

        userController.crearUsuario("Luis Martínez", "luis@example.com", "xyz789", listaDeportesUsar, listaNivelUsar,
                MedioNotificacion.SMS, new Ubicacion(5, 8));
        
        System.err.println(userController.getUsuarios()); 
        
        partidoController.crearPartido(Deporte.FUTBOL, 2, 10, new Ubicacion(1, 1), null, null, null, null, null, null);


        
/*
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
*/

        

    }
} 