package com.tpo.armarPartido.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;

public class ControllerUsuario {
    private static ControllerUsuario instancia;
    private List<Usuario> usuarios;

    private ControllerUsuario() {
        usuarios = new ArrayList<>();
    }

    public static ControllerUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ControllerUsuario();
        }
        return instancia;
    }

    public void crearUsuario(String nombre, String correo, String contrasena, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion, Ubicacion ubicacion) {
    	Usuario nuevo = new Usuario(nombre, correo, contrasena, deportesFavoritos, nivelesDeportes, medioNotificacion, ubicacion);
        usuarios.add(nuevo);
        System.err.println(nuevo.getNombre());
    }

    public void eliminarUsuario(String correo) {
        usuarios.removeIf(u -> u.getCorreo().equalsIgnoreCase(correo));
    }

    public void modificarUsuario(String correo, Usuario usuarioModificado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCorreo().equalsIgnoreCase(correo)) {
                usuarios.set(i, usuarioModificado);
                return;
            }
        }
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }


}

