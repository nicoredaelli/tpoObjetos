package com.tpo.armarPartido;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.model.UsuarioDTO;
import com.tpo.armarPartido.service.ControllerUsuario;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ControllerUsuario controller = new ControllerUsuario();

        // Crear un usuario válido
        UsuarioDTO dto = new UsuarioDTO(
            "Juan Pérez",
            "juan@example.com",
            List.of(Deporte.FUTBOL),
            List.of(Nivel.INTERMEDIO),
            MedioNotificacion.EMAIL
        );
        boolean creado = controller.crearUsuario(dto, "miContrasena123");
        System.out.println("Usuario creado: " + creado);

        // Intentar crear el mismo usuario (debería fallar)
        boolean creado2 = controller.crearUsuario(dto, "otraClave");
        System.out.println("Usuario creado (repetido): " + creado2);

        // Modificar usuario
        UsuarioDTO dtoMod = new UsuarioDTO(
            "Juan P.",
            "juan@example.com",
            List.of(Deporte.FUTBOL, Deporte.BASQUET),
            List.of(Nivel.AVANZADO),
            MedioNotificacion.SMS
        );
        boolean modificado = controller.modificarUsuario(dtoMod);
        System.out.println("Usuario modificado: " + modificado);

        // Eliminar usuario
        boolean eliminado = controller.eliminarUsuario(dto);
        System.out.println("Usuario eliminado: " + eliminado);

        // Listar usuarios (debería estar vacío)
        System.out.println("Usuarios actuales: " + controller.listarUsuarios().size());
    }
} 