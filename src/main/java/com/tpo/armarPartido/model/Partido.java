package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.service.EstrategiaEmparejamiento;
import com.tpo.armarPartido.service.estados.*;
import com.tpo.armarPartido.service.iObserver;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Partido {

    private Deporte deporte;
    private int cantidadJugadores;
    private int duracion;
    private Ubicacion ubicacion;
    private Date horario;
    private EstadoPartido estado;
    private EstrategiaEmparejamiento emparejamiento;
    private List<Usuario> jugadoresParticipan;
    private Nivel nivel;
    private List<iObserver> observadores; // Lista de Notificadores

    public Partido(Deporte deporte, int cantidadJugadores, int duracion, Ubicacion ubicacion, Date horario,
                   EstadoPartido estado, EstrategiaEmparejamiento emparejamiento, List<Usuario> jugadoresParticipan,
                   Nivel nivel, List<iObserver> observadores) {
        this.deporte = deporte;
        this.cantidadJugadores = cantidadJugadores;
        this.duracion = duracion;
        this.ubicacion = ubicacion;
        this.horario = horario;
        this.estado = estado;
        this.emparejamiento = emparejamiento;
        this.jugadoresParticipan = jugadoresParticipan;
        this.nivel = nivel;
        this.observadores = observadores;
    }

    @Override
	public String toString() {
		return "Partido [deporte=" + deporte + ", cantidadJugadores=" + cantidadJugadores + ", duracion=" + duracion
				+ ", ubicacion=" + ubicacion + ", horario=" + horario + ", estado=" + estado + ", emparejamiento="
				+ emparejamiento + ", jugadoresParticipan=" + jugadoresParticipan + ", nivel=" + nivel
				+ ", observadores=" + observadores + "]";
	}

	public void cambiarEstado(EstadoPartido nuevo) {
        EstadoPartido estadoAnterior = this.estado;
        this.estado = nuevo;
        System.out.println("++ Nuevo estado: " + this.getEstado());
        System.out.println("-----------------------------------");
        // Crear notificación con información del cambio
        notificarObservadores();
    }

    public void agregarJugador(Usuario jugador) {
        if (this.jugadoresParticipan.size() < this.cantidadJugadores) {
            jugadoresParticipan.add(jugador);
        } else {
            System.out.println("El equipo está completo.");
        }
    }

    public void agregarObservador(iObserver observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void quitarObservador(iObserver observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (iObserver observador : observadores) {
            try {
                observador.actualizar(this);
            } catch (Exception e) {
                System.err.printf("Error al notificar observador: %s%n", e.getMessage());
            }
        }
    }

    public void emparejarJugadores() {
        if (emparejamiento == null) {
            throw new IllegalStateException("No se definió una estrategia de emparejamiento");
        }
        this.jugadoresParticipan = emparejamiento.emparejar(this, this.jugadoresParticipan);
    }

    public void comentar(Usuario jugador, String mensaje) {
        if (this.estado instanceof Finalizado) {
            Comentario nuevoComentario = new Comentario(jugador, mensaje);
            ((Finalizado) this.estado).agregarComentario(nuevoComentario);
        } else {
            System.out.println("No se puede comentar. El partido aún no finalizó.");
        }
    }

    public int getCantidadJugadores() {
        return this.cantidadJugadores;
    }

    public Date getHorario() {
        return this.horario;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public List<Usuario> getJugadoresParticipan() {
        return this.jugadoresParticipan;
    }

    public Nivel getNivel() {
        return this.nivel;
    }

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public Deporte getDeporte() {
        return this.deporte;
    }
    
    public EstadoPartido getEstado() {
    	return this.estado;
    }
    public EstrategiaEmparejamiento getEmparejamiento() {
        return this.emparejamiento;
    }

    public void setEmparejamiento(EstrategiaEmparejamiento emparejamiento) {
        this.emparejamiento = emparejamiento;
    }
    public boolean esParticipante(Usuario jugador) {
    	boolean res = false;
    	for(Usuario usuario: jugadoresParticipan) {
    		if(usuario.equals(jugador)) {
    			res = true;
    			break;
    		}
    	}
    	return res;
    }


    public void setJugadoresParticipan(List<Usuario> jugadoresParticipan) {
        this.jugadoresParticipan = jugadoresParticipan;
    }
    
    public String getCreadorPartido(Partido partido) {
    	return partido.getJugadoresParticipan().get(0).toString();
    }


}
