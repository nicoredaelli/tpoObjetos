package com.tpo.armarPartido.model;

public class Notificacion {
    private String mensaje;
    private Usuario usuario;

    public Notificacion(String mensaje, Usuario usuario){
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
