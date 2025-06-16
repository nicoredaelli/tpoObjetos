package com.tpo.armarPartido.service.estados;

import com.tpo.armarPartido.model.*;

public class PartidoArmado implements EstadoPartido {

    private int confirmaciones = 0;
    private static final String mensaje = "Ya sos parte del partido %s! Nivel: %s Por favor envia tu confirmacion al partido del jugador:  %s \n -------------------------";

    public PartidoArmado() {}

    public int getConfirmaciones() {
        return confirmaciones;
    }

    public void setConfirmaciones(int confirmaciones) {
        this.confirmaciones = confirmaciones;
    }

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
        System.out.println("Un usuario confirmo! Tenemos "+ confirmaciones + " confirmaciones en total. " );
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
    public String getMessage(Partido partido) {
        return String.format(mensaje, partido.getDeporte(), partido.getNivel(), partido.getCreadorPartido(partido));
    }

	@Override
	public void comentar(Usuario jugador, String comentario) {
		// TODO Auto-generated method stub
		
	}
}