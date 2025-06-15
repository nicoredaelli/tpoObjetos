package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;

import com.tpo.armarPartido.service.iObserver;
import lombok.*;

import java.util.List;


public class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;
    private Ubicacion ubicacion;
    
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo + "]";
	}


	public String getCorreo() {
		return this.correo;
	}

	public List<Deporte> getDeportesFavoritos() {
		return this.deportesFavoritos;
	}

	public String getNombre() {

		return this.nombre;
	}

	public MedioNotificacion getMedioNotificacion() {
		return this.medioNotificacion;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public List<Nivel> getNivelesDeportes() {
		return this.nivelesDeportes;
	}

    public Usuario(String nombre, String correo, String contrasena, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.deportesFavoritos = deportesFavoritos;
        this.nivelesDeportes = nivelesDeportes;
        this.medioNotificacion = medioNotificacion;
        this.ubicacion = ubicacion;
    }

    public void actualizar(Notificacion notificacion) {
        // IMPLEMENTACIÓN BÁSICA
        // Simplemente mostrar la notificación en consola
        System.out.println("📧 [" + nombre + "] Notificación recibida: " + notificacion.getMensaje());
    }
}