package com.tpo.armarPartido.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comentarios")
public class Comentario {
    @Id
    private String id;
    private Usuario jugador;
    private String comentario;

    public Comentario(Usuario jugador, String comentario) {
        this.jugador = jugador;
        this.comentario = comentario;
    }

	public Usuario getJugador() {
        return jugador;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}