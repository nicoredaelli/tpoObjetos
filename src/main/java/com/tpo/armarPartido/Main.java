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
        
        
        // Previo a armar un partido necesito un usuario que lo cree y un horario del tipo Date. 
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22); // 22 horas
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date horario = (Date) calendar.getTime();
        Usuario Luis = userController.getUsuarioPorNombre("Luis Martinez");
        Usuario Ana = userController.getUsuarioPorNombre("Ana Gomez");
        Usuario Juan = userController.getUsuarioPorNombre("Juan Perez");
        
        partidoController.crearPartido(Deporte.FUTBOL, 2, 10, new Ubicacion(1, 1), horario, new EmparejamientoPorNivel(), Luis, Nivel.AVANZADO );
        partidoController.crearPartido(Deporte.VOLEY, 2, 10, new Ubicacion(1, 1), horario, new EmparejamientoPorNivel(), Ana, Nivel.INTERMEDIO );
        
        partidoController.buscarPartidosPorNivel(Nivel.AVANZADO);
        
        int idPartidoElegido = 0;
        partidoController.agregarJugadorAPartido(idPartidoElegido, Juan);
        partidoController.armarPartido(idPartidoElegido);
        
        System.out.println(partidoController.getPartidoID(idPartidoElegido));
        
        partidoController.confirmarPartido(0, Juan);
        partidoController.confirmarPartido(0, Ana);
        partidoController.confirmarPartido(0, Luis);
        

    }
} 