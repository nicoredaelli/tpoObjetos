package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;
import java.util.List;

/**
 * Objeto de transferencia de datos para Usuario (sin contraseña).
 */
public class UsuarioDTO {
    private final String nombre;
    private final String correo;
    private final List<Deporte> deportesFavoritos;
    private final List<Nivel> nivelesDeportes;
    private final MedioNotificacion medioNotificacion;

    /**
     * Constructor completo del DTO.
     * @param nombre Nombre del usuario
     * @param correo Correo electrónico
     * @param deportesFavoritos Lista de deportes favoritos
     * @param nivelesDeportes Lista de niveles de deportes
     * @param medioNotificacion Medio de notificación preferido
     */
    public UsuarioDTO(String nombre, String correo, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.deportesFavoritos = deportesFavoritos;
        this.nivelesDeportes = nivelesDeportes;
        this.medioNotificacion = medioNotificacion;
    }

    /** @return Nombre del usuario */
    public String getNombre() { return nombre; }
    /** @return Correo electrónico */
    public String getCorreo() { return correo; }
    /** @return Lista de deportes favoritos */
    public List<Deporte> getDeportesFavoritos() { return deportesFavoritos; }
    /** @return Lista de niveles de deportes */
    public List<Nivel> getNivelesDeportes() { return nivelesDeportes; }
    /** @return Medio de notificación preferido */
    public MedioNotificacion getMedioNotificacion() { return medioNotificacion; }
} 