package com.tpo.armarPartido.service.estados;

import com.tpo.armarPartido.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class PartidoArmado implements EstadoPartido {

    private int confirmaciones = 0;
    private static final String mensaje = "Ya se confirmaron %d jugadores";

    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new Cancelado());
    }
    
    @Override
	public String toString() {
		return "PartidoArmado";
	}

	@Override
    public void armar(Partido partido) {
        // No aplica. El partido ya está armado.
    }

    @Override
    public void confirmar(Partido partido) {
        confirmaciones++;
        System.out.println("Un usuario confirmo! Tenemos "+ confirmaciones + " en total. " );
        if (confirmaciones >= partido.getJugadoresParticipan().size()) {
            partido.cambiarEstado(new Confirmacion());
            
            
        }
    }

    @Override
    public void comenzar(Partido partido) {
        // No aplica.
    }

    @Override
    public void finalizar(Partido partido) {
        // No aplica.
    }

    @Override
    public String getMessage() {
        return String.format(mensaje, confirmaciones);
    }
}