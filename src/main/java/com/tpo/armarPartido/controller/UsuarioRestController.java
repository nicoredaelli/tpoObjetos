package com.tpo.armarPartido.controller;

import com.tpo.armarPartido.dto.UsuarioDTO;
import com.tpo.armarPartido.dto.DTOMapper;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(DTOMapper::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable String id) {
        return usuarioRepository.findById(id)
                .map(DTOMapper::toUsuarioDTO)
                .orElse(null);
    }

    // Crear un usuario (requiere password en el body)
    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO, @RequestParam String contrasena) {
        Usuario usuario = DTOMapper.toUsuario(usuarioDTO, contrasena);
        Usuario saved = usuarioRepository.save(usuario);
        return DTOMapper.toUsuarioDTO(saved);
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
    }
} 