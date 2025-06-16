package com.tpo.armarPartido.controller;

import com.tpo.armarPartido.dto.PartidoDTO;
import com.tpo.armarPartido.dto.DTOMapper;
import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.tpo.armarPartido.service.estados.EstadoPartido;
import com.tpo.armarPartido.service.estados.NecesitamosJugadores;
import com.tpo.armarPartido.service.estados.PartidoArmado;
import com.tpo.armarPartido.service.estados.Finalizado;
import com.tpo.armarPartido.service.estados.Confirmacion;
import com.tpo.armarPartido.service.estados.EnJuego;
import com.tpo.armarPartido.service.estados.Cancelado;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/partidos")
public class PartidoRestController {

    @Autowired
    private PartidoRepository partidoRepository;

    // Crear partido
    @PostMapping
    public ResponseEntity<PartidoDTO> crearPartido(@RequestBody PartidoDTO partidoDTO) {
        Partido partido = new Partido();
        partido.setDeporte(partidoDTO.getDeporte());
        partido.setCantidadJugadores(partidoDTO.getCantidadJugadores());
        partido.setDuracion(partidoDTO.getDuracion());
        partido.setUbicacion(partidoDTO.getUbicacion());
        partido.setHorario(partidoDTO.getHorario());
        partido.setNivel(partidoDTO.getNivel());
        // Mapear jugadoresParticipan si es necesario (aquí se asume vacío o mapeo simple)
        // Estado
        String estado = partidoDTO.getEstado();
        switch (estado) {
            case "PENDIENTE":
                partido.setEstado(new NecesitamosJugadores());
                break;
            case "ARMADO":
                partido.setEstado(new PartidoArmado());
                break;
            case "FINALIZADO":
                partido.setEstado(new Finalizado());
                break;
            case "CONFIRMACION":
                partido.setEstado(new Confirmacion());
                break;
            case "ENJUEGO":
                partido.setEstado(new EnJuego());
                break;
            case "CANCELADO":
                partido.setEstado(new Cancelado());
                break;
            default:
                partido.setEstado(new NecesitamosJugadores()); // Valor por defecto
        }
        Partido partidoGuardado = partidoRepository.save(partido);
        return new ResponseEntity<>(DTOMapper.toPartidoDTO(partidoGuardado), HttpStatus.CREATED);
    }

    // Listar todos los partidos
    @GetMapping
    public List<PartidoDTO> listarPartidos() {
        return partidoRepository.findAll().stream()
                .map(DTOMapper::toPartidoDTO)
                .collect(Collectors.toList());
    }

    // Obtener partido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> obtenerPartido(@PathVariable String id) {
        Optional<Partido> partidoOpt = partidoRepository.findById(id);
        if (partidoOpt.isPresent()) {
            return ResponseEntity.ok(DTOMapper.toPartidoDTO(partidoOpt.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar partido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable String id) {
        if (partidoRepository.existsById(id)) {
            partidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 