package com.tpo.armarPartido.model;

import com.tpo.armarPartido.dtos.UsuarioDTO;
import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.service.EstrategiaEmparejamiento;
import com.tpo.armarPartido.service.estados.*;
import com.tpo.armarPartido.service.iObserver;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<iObserver> observadores;

    



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

	public void cambiarEstado(EstadoPartido nuevo) {
        EstadoPartido estadoAnterior = this.estado;
        this.estado = nuevo;
        // notificarObservadores();
        // Chequear esto con Ilan - Explicacion de flujo
    }

    public void emparejarJugadores() {
        if (emparejamiento == null) {
            throw new IllegalStateException("No se definio una estrategia de emparejamiento");
        }
        this.jugadoresParticipan = emparejamiento.emparejar(this, this.jugadoresParticipan);

    }


    public void agregarObservador(iObserver o) {
        if (o != null && !observadores.contains(o)) {
            observadores.add(o);
        }
    }

    public void quitarObservador(iObserver o) {
        observadores.remove(o);
    }

    public void notificarObservadores(String estado) {
        for (iObserver o : observadores) {
            try {
                // Chequear esto con Ilan - Explicacion de flujo
                // o.update(new Notificacion(estado));
            } catch (Exception e) {
                System.err.printf("Error al notificar observador: %s%n", e.getMessage());
            }
        }
    }

    public void agregarJugador(Usuario jugador) {

        if (this.cantidadJugadores >= this.jugadoresParticipan.size()) {
            jugadoresParticipan.add(jugador);
            System.out.println("Se agrego el jugador " + jugador + " con exito. ");
        }
        else {System.out.println("El equipo esta completo.");}
    }

    public void comentar(UsuarioDTO jugador, String mensaje) {
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

	public Object getDeporte() {
		return this.deporte;
	}

}

	
	

	
	