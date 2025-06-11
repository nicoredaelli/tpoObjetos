package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;
    private Ubicacion ubicacion;

} 