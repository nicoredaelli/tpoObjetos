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
        
        

    }
} 