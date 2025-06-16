package com.tpo.armarPartido;

import java.util.*;

import com.tpo.armarPartido.controller.ControllerPartido;
import com.tpo.armarPartido.controller.ControllerUsuario;
import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.EmparejamientoPorNivel;
import com.tpo.armarPartido.service.EmparejamientoPorUbicacion;

import utils.GenerarPartidos;
import utils.GenerarUsuarios;

public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("Inicio App ArmarPartido");
    
    	// Creo controlador y usuarios. 
    	ControllerUsuario userController = ControllerUsuario.getInstancia();
    	ControllerPartido partidoController = ControllerPartido.getInstancia();
    	
    	// Genero Usuarios
    	GenerarUsuarios.generar(userController);    
        
    	// Genero Partidos
    	GenerarPartidos.generar(userController, partidoController);

    	// Instancio usuarios para Usar
        Usuario Luis = userController.getUsuarioPorNombre("Luis Martinez");
        Usuario Ana = userController.getUsuarioPorNombre("Ana Gomez");
        Usuario Juan = userController.getUsuarioPorNombre("Juan Perez");
        
        // Busco los partidos por nivel, por ejemplo avanzado 
        partidoController.buscarPartidosPorNivel(Nivel.AVANZADO);
        // Armo el partido que elegi y se agregan de jugadores a partir de la estrategia definida en el partido. 
        int idPartidoElegido = 0;
        partidoController.armarPartido(idPartidoElegido);
        
        System.out.println(partidoController.getPartidoID(idPartidoElegido));
        
        partidoController.confirmarPartido(0, Juan);
        partidoController.confirmarPartido(0, Ana);
        
        
    }
} 