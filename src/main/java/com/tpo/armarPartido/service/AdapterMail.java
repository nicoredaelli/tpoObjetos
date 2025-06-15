package com.tpo.armarPartido.service;

import com.tpo.armarPartido.model.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AdapterMail implements AdapterNotificacionMail {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void notificar(Notificacion notificacion) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("tu-email@gmail.com");
            message.setTo(notificacion.getUsuario().getNombre());
            message.setSubject("Notificación de Partido");
            message.setText(notificacion.getMensaje());

            mailSender.send(message);
            System.out.println("Email enviado exitosamente a: " + notificacion.getUsuario().getNombre());

        } catch (Exception e) {
            System.err.println("Error enviando email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}