package com.tpo.armarPartido.model;


import lombok.*;

@Getter
@Setter
public class Comentario {
    private Usuario jugador;
    private String comentario;

    public Comentario(Usuario jugador, String comentario) {
        this.jugador = jugador;
        this.comentario = comentario;
    }

    public Usuario getJugador() {
        return jugador;
    }

    public String getComentario() {
        return comentario;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}