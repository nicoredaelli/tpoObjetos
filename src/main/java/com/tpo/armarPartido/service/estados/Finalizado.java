package com.tpo.armarPartido.service.estados;

import com.tpo.armarPartido.model.Partido;
import java.util.List;
import com.tpo.armarPartido.model.Comentario;

public class Finalizado implements EstadoPartido {

    private static final String mensaje = "El partido ya finalizo! \n -------------------------";
    private List<Comentario> comentarios;

    public Finalizado() {}

    public Finalizado(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
	public String toString() {
		return "Finalizado []";
	}

	@Override
    public void cancelar(Partido partido) {
        // No aplica.

    }

    @Override
    public void armar(Partido partido) {
        // No aplica.

    }

    @Override
    public void confirmar(Partido partido) {
        // No aplica.

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
        return mensaje;

    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

}