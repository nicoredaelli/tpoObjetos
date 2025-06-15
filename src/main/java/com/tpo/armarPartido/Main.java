package com.tpo.armarPartido;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tpo.armarPartido.controller.ControllerPartido;
import com.tpo.armarPartido.controller.ControllerUsuario;
import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.EmparejamientoPorNivel;

public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("Inicio App ArmarPartido");
    
    	// Creo controlador y usuarios. 
    	ControllerUsuario userController = ControllerUsuario.getInstancia();
    	ControllerPartido partidoController = ControllerPartido.getInstancia();
    	
    	List<Deporte> listaDeportesUsar = new ArrayList();
    	listaDeportesUsar.add(Deporte.BASQUET);
    	List<Nivel> listaNivelUsar = new ArrayList<>();
    	listaNivelUsar.add(Nivel.AVANZADO);
    	
    	userController.crearUsuario("Juan Perez", "juan@example.com", "1234", listaDeportesUsar, listaNivelUsar
    			, MedioNotificacion.EMAIL, new Ubicacion(1, 1));
    	
        userController.crearUsuario("Ana Gomez", "ana@example.com", "abcd", listaDeportesUsar, listaNivelUsar,
                MedioNotificacion.SMS, new Ubicacion(2, 3));

        userController.crearUsuario("Luis Martinez", "luis@example.com", "xyz789", listaDeportesUsar, listaNivelUsar,
                MedioNotificacion.SMS, new Ubicacion(5, 8));
        
        System.err.println(userController.getUsuarios()); 
        
        // Previo a armar un partido necesito un usuario que lo cree y un horario del tipo Date. 
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22); // 22 horas
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date horario = (Date) calendar.getTime();
        Usuario usurioCreaPartido = userController.getUsuarioPorNombre("Luis Martinez");
        
        partidoController.crearPartido(Deporte.FUTBOL, 2, 10, new Ubicacion(1, 1), horario, new EmparejamientoPorNivel(), usurioCreaPartido, Nivel.AVANZADO );
        partidoController.buscarPartidosPorNivel(Nivel.AVANZADO);
        
        Usuario usuarioNuevoDelPartido = userController.getUsuarioPorNombre("Juan Perez");
        partidoController.agregarJugadorAPartido(0, usuarioNuevoDelPartido);
        

        
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