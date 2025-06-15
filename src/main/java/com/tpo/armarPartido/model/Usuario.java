package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;

import com.tpo.armarPartido.service.iObserver;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class Usuario implements iObserver {
    private String nombre;
    private String correo;
    private String contrasena;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;
    private Ubicacion ubicacion;

    public Usuario(String nombre, String correo, String contrasena, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.deportesFavoritos = deportesFavoritos;
        this.nivelesDeportes = nivelesDeportes;
        this.medioNotificacion = medioNotificacion;
        this.ubicacion = ubicacion;
    }

    @Override
    public void actualizar(Notificacion notificacion) {
        // IMPLEMENTACIÓN BÁSICA
        // Simplemente mostrar la notificación en consola
        System.out.println("📧 [" + nombre + "] Notificación recibida: " + notificacion.getMensaje());
    }
}