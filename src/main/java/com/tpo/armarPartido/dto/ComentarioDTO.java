package com.tpo.armarPartido.dto;

public class ComentarioDTO {
    private String jugadorNombre;
    private String comentario;

    public ComentarioDTO() {}

    public ComentarioDTO(String jugadorNombre, String comentario) {
        this.jugadorNombre = jugadorNombre;
        this.comentario = comentario;
    }

    public String getJugadorNombre() {
        return jugadorNombre;
    }

    public void setJugadorNombre(String jugadorNombre) {
        this.jugadorNombre = jugadorNombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
} 