package com.tpo.armarPartido.controller;

import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Controlador para la gestión de usuarios: creación, modificación, eliminación y búsqueda.
 */
public class ControllerUsuario {
    private List<Usuario> usuarios;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Constructor: inicializa la lista de usuarios.
     */
    public ControllerUsuario() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Crea un nuevo usuario si el correo no existe y los datos son válidos.
     * @param dto DTO con los datos del usuario
     * @param contrasena Contraseña del usuario
     * @return true si se creó correctamente, false en caso contrario
     */
    public boolean crearUsuario(UsuarioDTO dto, String contrasena) {
        if (!validarCampos(dto) || !validarCorreo(dto.getCorreo()) || contrasena == null || contrasena.isEmpty()) {
            return false;
        }
        if (buscarUsuarioPorCorreo(dto.getCorreo()).isPresent()) {
            return false;
        }
        Usuario nuevoUsuario = new Usuario(
            dto.getNombre(),
            dto.getCorreo(),
            contrasena,
            dto.getDeportesFavoritos(),
            dto.getNivelesDeportes(),
            dto.getMedioNotificacion()
        );
        usuarios.add(nuevoUsuario);
        return true;
    }

    /**
     * Modifica un usuario existente si los datos son válidos.
     * @param dto DTO con los datos a modificar
     * @return true si se modificó correctamente, false si no se encontró o datos inválidos
     */
    public boolean modificarUsuario(UsuarioDTO dto) {
        if (!validarCampos(dto) || !validarCorreo(dto.getCorreo())) {
            return false;
        }
        Optional<Usuario> usuarioOpt = buscarUsuarioPorCorreo(dto.getCorreo());
        if (usuarioOpt.isEmpty()) {
            return false;
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setNombre(dto.getNombre());
        usuario.setDeportesFavoritos(dto.getDeportesFavoritos());
        usuario.setNivelesDeportes(dto.getNivelesDeportes());
        usuario.setMedioNotificacion(dto.getMedioNotificacion());
        return true;
    }

    /**
     * Elimina un usuario existente por correo.
     * @param dto DTO con el correo del usuario a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarUsuario(UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = buscarUsuarioPorCorreo(dto.getCorreo());
        if (usuarioOpt.isEmpty()) {
            return false;
        }
        usuarios.remove(usuarioOpt.get());
        return true;
    }

    /**
     * Busca un usuario por correo.
     * @param correo Correo a buscar
     * @return Optional con el usuario si existe, vacío si no
     */
    public Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return usuarios.stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                .findFirst();
    }

    /**
     * Lista todos los usuarios.
     * @return Lista de usuarios
     */
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // --- Métodos privados de validación ---
    private boolean validarCampos(UsuarioDTO dto) {
        return dto != null &&
               dto.getNombre() != null && !dto.getNombre().isEmpty() &&
               dto.getCorreo() != null && !dto.getCorreo().isEmpty() &&
               dto.getDeportesFavoritos() != null && !dto.getDeportesFavoritos().isEmpty() &&
               dto.getNivelesDeportes() != null && !dto.getNivelesDeportes().isEmpty() &&
               dto.getMedioNotificacion() != null;
    }

    private boolean validarCorreo(String correo) {
        return correo != null && EMAIL_PATTERN.matcher(correo).matches();
    }
} 