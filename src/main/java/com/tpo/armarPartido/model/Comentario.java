package com.tpo.armarPartido.model;

import com.tpo.armarPartido.dtos.UsuarioDTO;
import com.tpo.armarPartido.dtos.UsuarioDTO;
import lombok.*;

@Getter
@Setter
public class Comentario {
    private UsuarioDTO jugador;
    private String comentario;

    public Comentario(UsuarioDTO jugador, String comentario) {
        this.jugador = jugador;
        this.comentario = comentario;
    }

}