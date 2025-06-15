package com.tpo.armarPartido.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

	
@Builder
@Getter
@Setter
@AllArgsConstructor
public class ComentarioDTO {
    private UsuarioDTO jugador;
    private String comentario;
	public ComentarioDTO(UsuarioDTO jugador, String comentario) {
		super();
		this.jugador = jugador;
		this.comentario = comentario;
	}
	public UsuarioDTO getJugador() {
		return jugador;
	}
	public void setJugador(UsuarioDTO jugador) {
		this.jugador = jugador;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
    
}
