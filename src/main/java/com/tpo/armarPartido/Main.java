package com.tpo.armarPartido;

import com.tpo.armarPartido.controller.ControllerPartido;
import com.tpo.armarPartido.controller.ControllerUsuario;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Usuario;
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
        
        partidoController.confirmarPartido(0, Luis);
        partidoController.confirmarPartido(0, Juan);
        
        
        
        
    }
} 