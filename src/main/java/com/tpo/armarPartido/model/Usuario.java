package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;

import java.util.Map;

public class Usuario {
	private String nombre;
	private String correo;
	private String contrasena;
	private Map<Deporte, Nivel> nivelesPorDeporte;
	private MedioNotificacion medioNotificacion;
	private Ubicacion ubicacion;

	public Usuario(String nombre, String correo, String contrasena,
				   Map<Deporte, Nivel> nivelesPorDeporte,
				   MedioNotificacion medioNotificacion, Ubicacion ubicacion) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.nivelesPorDeporte = nivelesPorDeporte;
		this.medioNotificacion = medioNotificacion;
		this.ubicacion = ubicacion;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public Map<Deporte, Nivel> getNivelesPorDeporte() {
		return nivelesPorDeporte;
	}

	public void setNivelesPorDeporte(Map<Deporte, Nivel> nivelesPorDeporte) {
		this.nivelesPorDeporte = nivelesPorDeporte;
	}

	public MedioNotificacion getMedioNotificacion() {
		return medioNotificacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo + "]";
	}

	public void actualizar(Notificacion notificacion) {
		System.out.println("📧 [" + nombre + "] Notificación recibida: " + notificacion.getMensaje());
	}
}
