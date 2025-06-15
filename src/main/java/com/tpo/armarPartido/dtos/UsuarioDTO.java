package com.tpo.armarPartido.dtos;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.model.Ubicacion;
import lombok.*;

import java.util.List;

/**
 * Objeto de transferencia de datos para Usuario (sin contraseña).
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String correo;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;
    private Ubicacion ubicacion;
    
    
	public UsuarioDTO(String nombre, String correo, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes,
			MedioNotificacion medioNotificacion, Ubicacion ubicacion) {
		this.nombre = nombre;
		this.correo = correo;
		this.deportesFavoritos = deportesFavoritos;
		this.nivelesDeportes = nivelesDeportes;
		this.medioNotificacion = medioNotificacion;
		this.ubicacion = ubicacion;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<Deporte> getDeportesFavoritos() {
		return deportesFavoritos;
	}
	public void setDeportesFavoritos(List<Deporte> deportesFavoritos) {
		this.deportesFavoritos = deportesFavoritos;
	}
	public List<Nivel> getNivelesDeportes() {
		return nivelesDeportes;
	}
	public void setNivelesDeportes(List<Nivel> nivelesDeportes) {
		this.nivelesDeportes = nivelesDeportes;
	}
	public MedioNotificacion getMedioNotificacion() {
		return medioNotificacion;
	}
	public void setMedioNotificacion(MedioNotificacion medioNotificacion) {
		this.medioNotificacion = medioNotificacion;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
    
    
}