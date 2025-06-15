package com.tpo.armarPartido.model;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.service.EstrategiaEmparejamiento;
import com.tpo.armarPartido.service.EstrategiaNotificacion;
import com.tpo.armarPartido.service.estados.EstadoPartido;
import com.tpo.armarPartido.service.estados.Finalizado;
import com.tpo.armarPartido.service.iObserver;
import lombok.*;

import java.util.ArrayList;
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
    private Notificador notificador;
    private Nivel nivel;

    public Partido(Nivel nivel, List<Usuario> jugadoresParticipan, EstrategiaEmparejamiento emparejamiento,
                   EstadoPartido estado, Date horario, Ubicacion ubicacion, int duracion,
                   int cantidadJugadores, Deporte deporte) {
        this.nivel = nivel;
        this.jugadoresParticipan = jugadoresParticipan;
        this.emparejamiento = emparejamiento;
        this.estado = estado;
        this.horario = horario;
        this.ubicacion = ubicacion;
        this.duracion = duracion;
        this.cantidadJugadores = cantidadJugadores;
        this.deporte = deporte;
        this.notificador = new Notificador();
    }

    public void cambiarEstado(EstadoPartido nuevo) {
        EstadoPartido estadoAnterior = this.estado;
        this.estado = nuevo;

        // Usar el notificador para enviar notificaciones
        if (notificador != null) {
            notificador.notificar(new Notificacion(nuevo.getMessage()));
        }
    }

    public void emparejarJugadores() {
        if (emparejamiento == null) {
            throw new IllegalStateException("No se definio una estrategia de emparejamiento");
        }
        this.jugadoresParticipan = emparejamiento.emparejar(this, this.jugadoresParticipan);
    }

    public void agregarJugador(Usuario jugador) {
        if (this.cantidadJugadores >= this.jugadoresParticipan.size()) {
            jugadoresParticipan.add(jugador);
            System.out.println("Se agrego el jugador " + jugador + " con exito. ");
        } else {
            System.out.println("El equipo esta completo.");
        }
    }

    public void comentar(Usuario jugador, String mensaje) {
        if (this.estado instanceof Finalizado) {
            Comentario nuevoComentario = new Comentario(jugador, mensaje);
            ((Finalizado) this.estado).agregarComentario(nuevoComentario);
        } else {
            System.out.println("No se puede comentar. El partido aún no finalizó.");
        }
    }

    public void agregarObservador(iObserver observador) {
        if (notificador != null) {

            notificador.agregarObservador(observador);
        }
    }

    public void quitarObservador(iObserver observador) {
        if (notificador != null) {
            notificador.quitarObservador(observador);
        }
    }

    // Método para cambiar la estrategia de notificación desde Partido
    public void cambiarEstrategiaNotificacion(EstrategiaNotificacion estrategia) {
        if (notificador != null) {
            notificador.cambiarEstrategiaNotificacion(estrategia);
        }
    }

    // Getters y setters existentes...
    public Deporte getDeporte() {
        return deporte;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public int getDuracion() {
        return duracion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Date getHorario() {
        return horario;
    }

    public EstadoPartido getEstado() {
        return estado;
    }

    public EstrategiaEmparejamiento getEmparejamiento() {
        return emparejamiento;
    }

    public List<Usuario> getJugadoresParticipan() {
        return jugadoresParticipan;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    // Setters...
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setJugadoresParticipan(List<Usuario> jugadoresParticipan) {
        this.jugadoresParticipan = jugadoresParticipan;
    }

    public void setEmparejamiento(EstrategiaEmparejamiento emparejamiento) {
        this.emparejamiento = emparejamiento;
    }

    public void setEstado(EstadoPartido estado) {
        this.estado = estado;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }
}
	

	
	