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
    private final String nombre;
    private final String correo;
    private final List<Deporte> deportesFavoritos;
    private final List<Nivel> nivelesDeportes;
    private final MedioNotificacion medioNotificacion;
    private Ubicacion ubicacion;
}