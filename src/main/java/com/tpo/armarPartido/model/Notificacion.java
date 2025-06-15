package com.tpo.armarPartido.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class Notificacion {
    private final String mensaje;

    public Notificacion(String mensaje){
        this.mensaje = mensaje;
    }

	public String getMensaje() {
		return this.mensaje;
	}



}
