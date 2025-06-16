package com.tpo.armarPartido.dto;

import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.model.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DTOMapper {
    // Usuario -> UsuarioDTO
    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setNivelesPorDeporte(usuario.getNivelesPorDeporte());
        dto.setMedioNotificacion(usuario.getMedioNotificacion());
        dto.setUbicacion(usuario.getUbicacion());
        return dto;
    }

    // UsuarioDTO -> Usuario (for creation, password must be handled separately)
    public static Usuario toUsuario(UsuarioDTO dto, String contrasena) {
        if (dto == null) return null;
        Usuario usuario = new Usuario(
            dto.getNombre(),
            dto.getCorreo(),
            contrasena,
            dto.getNivelesPorDeporte(),
            dto.getMedioNotificacion(),
            dto.getUbicacion()
        );
        usuario.setId(dto.getId());
        return usuario;
    }

    // Partido -> PartidoDTO
    public static PartidoDTO toPartidoDTO(Partido partido) {
        if (partido == null) return null;
        List<UsuarioDTO> jugadoresDTO = partido.getJugadoresParticipan() == null ? null :
            partido.getJugadoresParticipan().stream().map(DTOMapper::toUsuarioDTO).collect(Collectors.toList());
        PartidoDTO dto = new PartidoDTO(
            partido.getDeporte(),
            partido.getCantidadJugadores(),
            partido.getDuracion(),
            partido.getUbicacion(),
            partido.getHorario(),
            partido.getEstado() != null ? partido.getEstado().toString() : null,
            partido.getNivel(),
            jugadoresDTO
        );
        dto.setId(partido.getId());
        return dto;
    }

    // Comentario -> ComentarioDTO
    public static ComentarioDTO toComentarioDTO(Comentario comentario) {
        if (comentario == null) return null;
        return new ComentarioDTO(
            comentario.getJugador() != null ? comentario.getJugador().getNombre() : null,
            comentario.getComentario()
        );
    }

    // Optionally, add more mapping methods as needed
} 