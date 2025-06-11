package com.tpo.armarPartido.dtos;

import java.util.Date;
import java.util.List;

import com.tpo.armarPartido.dtos.UsuarioDTO;
import com.tpo.armarPartido.enums.*;
import com.tpo.armarPartido.model.*;
import com.tpo.armarPartido.service.*;
import com.tpo.armarPartido.service.estados.EstadoPartido;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidoDTO {

    private Deporte deporte;
    private int cantidadJugadores;
    private int duracion;
    private Ubicacion ubicacion;
    private Date horario;
    private EstadoPartido estado;
    private EstrategiaEmparejamiento emparejamiento;
    private List<UsuarioDTO> jugadores;
    private List<ComentarioDTO> comentarios;

}