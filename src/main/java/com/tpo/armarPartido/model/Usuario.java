package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;
import java.util.List;

public class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;

    public Usuario(String nombre, String correo, String contrasena, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.deportesFavoritos = deportesFavoritos;
        this.nivelesDeportes = nivelesDeportes;
        this.medioNotificacion = medioNotificacion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public List<Deporte> getDeportesFavoritos() { return deportesFavoritos; }
    public void setDeportesFavoritos(List<Deporte> deportesFavoritos) { this.deportesFavoritos = deportesFavoritos; }
    public List<Nivel> getNivelesDeportes() { return nivelesDeportes; }
    public void setNivelesDeportes(List<Nivel> nivelesDeportes) { this.nivelesDeportes = nivelesDeportes; }
    public MedioNotificacion getMedioNotificacion() { return medioNotificacion; }
    public void setMedioNotificacion(MedioNotificacion medioNotificacion) { this.medioNotificacion = medioNotificacion; }
} 