package com.tpo.armarPartido.service.estados;

import com.tpo.armarPartido.model.Partido;

public class Cancelado implements EstadoPartido {
    private static final String mensaje = "El partido se cancelo.";

    @Override
	public String toString() {
		return "Cancelado";
	}

	@Override
    public void cancelar(Partido partido) {
        // No aplica a este estado

    }

    @Override
    public void armar(Partido partido) {
        // No aplica a este estado

    }

    @Override
    public void confirmar(Partido partido) {
        // No aplica a este estado

    }

    @Override
    public void comenzar(Partido partido) {
        // No aplica a este estado

    }

    @Override
    public void finalizar(Partido partido) {
        // No aplica a este estado

    }

    @Override
    public String getMessage() {
        return mensaje;

    }

}